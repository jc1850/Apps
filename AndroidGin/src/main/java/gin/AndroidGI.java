package gin;

import com.opencsv.CSVWriter;
import gin.edit.Edit;
import gin.edit.statement.*;
import gin.test.AndroidTestResult;
import gin.test.AndroidTestRunner;
import gin.test.AndroidUnitTestResultSet;
import gin.util.*;
import gin.util.NSGA.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.rng.simple.JDKRandomBridge;
import org.apache.commons.rng.simple.RandomSource;
import org.pmw.tinylog.Logger;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class AndroidGI {
    AndroidTestRunner testRunner;
    SourceFile sourceFile;
    List<Class<? extends Edit>> editTypes;
    JDKRandomBridge rng;
    int indNumber = 50;
    int genNumber = 20;
    int NO_RUNS = 5;
    private double tournamentPercentage = 0.2;
    private double mutateProbability = 0.5;
    private File outputFile;
    AndroidProject project;
    CSVWriter outputFileWriter;
    Map<String, AndroidUnitTestResultSet> book;

    public AndroidGI(AndroidTestRunner testRunner, SourceFileTree sourceFile, List<Class<? extends Edit>> editTypes, AndroidProject project) {
        long seed = Instant.now().getEpochSecond();
        this.testRunner = testRunner;
        this.sourceFile = sourceFile;
        this.editTypes = editTypes;
        this.rng = new JDKRandomBridge(RandomSource.MT, seed);
        outputFile = new File("log.txt");
        this.project = project;

    }

    public static void main(String[] args) {

        AndroidConfigReader configReader = new AndroidConfigReader("config.properties");
        AndroidConfig config = configReader.readConfig();
        String fileName = config.getFilePath();
        AndroidProject androidProject = new AndroidProject(config);
        AndroidTestRunner testRunner = new AndroidTestRunner(androidProject, config);
        //testRunner.installTestAPK();
        List<Class<? extends Edit>> editTypes = Edit.parseEditClassesFromString(Edit.EditType.STATEMENT.toString());
        List<String> targetMethod = config.getTargetMethods();
        SourceFileTree sourceFile =  (SourceFileTree) SourceFile.makeSourceFileForEditTypes(editTypes, fileName, targetMethod,androidProject.getClasspath());

        long seed = Instant.now().getEpochSecond();
        AndroidGI androidGI = new AndroidGI(testRunner, sourceFile, editTypes, androidProject);

//        ClassCacheEdit edit = new ClassCacheEdit(sourceFile, androidGI.rng);
//        edit.apply(sourceFile);

//        androidGI.localSearch();
        SPEA2 spea2 = new SPEA2(androidProject, sourceFile, testRunner, editTypes);
        spea2.run();
        //androidGI.testPatches("patches.txt");
        //androidGI.timeLaunches("patches.txt");
        //androidGI.getDiffs("patches.txt");
        System.exit(0);
    }


    public AndroidUnitTestResultSet getOriginalJank() {
        try {
            Patch patch = new Patch(sourceFile);
            AndroidUnitTestResultSet results = testRunner.runTests(patch, true, 3);
            if (!results.isPatchValid()) {
                System.out.println("Tests Failed on original app");
                System.exit(1);
            }
            for (AndroidTestResult result : results.getResults()) {
                System.out.println(result);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public void localSearch() {
        Map<String, AndroidUnitTestResultSet> tested = new HashMap();
        writeHeader();
        Patch patch = new Patch(sourceFile);
        AndroidUnitTestResultSet origRes = testPatchLocally(patch, true, 2);
        AndroidUnitTestResultSet bestresults = null;
        double originalTime = origRes.getMemoryUsage();
        if (!origRes.isPatchValid()){
            System.out.println("Tests failed on original app");
            System.exit(1);
        }
        Patch bestPatch = new Patch(this.sourceFile);
        writePatch(bestPatch, origRes, -1);
        tested.put(bestPatch.toString(), origRes);

        double bestTime = originalTime;
        for (int step = 0; step < 400; step++) {

            try {
                if (step % 5 == 0) {
                    project.killDaemon();
                }
                AndroidUnitTestResultSet results;

                Patch neighbour = neighbour(bestPatch);
                if (!tested.containsKey(neighbour.toString())) {
                    results = testPatchLocally(neighbour, true, 2);

                } else {
                    results = tested.get(neighbour.toString());
                }
                writePatch(neighbour, results, step);
                if (!results.isPatchValid()) {

                } else if (results.getMemoryUsage() < bestTime) {
                    bestPatch = neighbour;
                    bestTime = results.getMemoryUsage();
                    bestresults = results;
                }
            } catch (Exception e) {
                bestPatch.undoWrite(sourceFile.getFilename());
                e.printStackTrace();
                System.exit(1);
            }

        }
        System.out.println(bestPatch);
        System.out.println(originalTime);
        System.out.println(bestTime);
        if(bestresults == null){
            bestresults = origRes;
        }
        writePatch(bestPatch, bestresults, -2);

    }


    Patch neighbour(Patch patch) {

        Patch neighbour = patch.clone();
        neighbour.addRandomEditOfClasses(rng, editTypes);
        return neighbour;

    }


    public void GP() {

        writeHeader();
        AndroidUnitTestResultSet results;
        // Calculate fitness and record result, including fitness improvement (currently 0)
        AndroidUnitTestResultSet origRes = getOriginalJank();
        double orig = origRes.getJankiness();
        Patch origPatch = new Patch(sourceFile);
        writePatch(origPatch, origRes, -1);
        // Keep best
        double best = orig;

        // Generation 1
        Map<Patch, Double> population = new HashMap<>();
        population.put(origPatch, orig);

        for (int i = 1; i < indNumber; i++) {

            // Add a mutation
            Patch patch = mutate(origPatch);
            // If fitnessThreshold met, add it
            results = testPatch(patch);
            if (fitnessThreshold(results, orig)) {
                population.put(patch, fitness(results));
            }
            writePatch(patch, results, 0);

        }
        int count = 0;
        for (int g = 0; g < genNumber; g++) {

            // Previous generation
            List<Patch> patches = new ArrayList(population.keySet());

            Logger.info("Creating generation: " + (g + 1));

            // Current generation
            Map<Patch, Double> newPopulation = new HashMap<>();

            // Select individuals for crossover
            List<Patch> selectedPatches = select(population, origPatch, orig);

            // Keep a list of patches after crossover
            List<Patch> crossoverPatches = crossover(selectedPatches, origPatch);

            // If less than indNumber variants produced, add random patches from the previous generation
            while (crossoverPatches.size() < indNumber) {
                crossoverPatches.add(patches.get(rng.nextInt(patches.size())).clone());
            }

            // Mutate the newly created population and check fitness
            for (Patch patch : crossoverPatches) {

                // Add a mutation
                patch = mutate(patch);

                Logger.debug("Testing patch: " + patch);

                // Test the patched source file
                results = testPatch(patch);
                count += 1;
                double newFitness = fitness(results);
                if (count >= 10) {
                    count = 0;
                    project.killDaemon();
                }

                // If fitness threshold met, add patch to the mating population
                if (fitnessThreshold(results, orig)) {
                    newPopulation.put(patch, newFitness);
                }
                writePatch(patch, results, g);
            }

            population = new HashMap<Patch, Double>(newPopulation);
            if (population.isEmpty()) {
                population.put(origPatch, orig);
            }


        }

    }


    protected double fitness(AndroidUnitTestResultSet results) {

        double fitness = results.getJankiness();
        return fitness;
    }

    // Calculate fitness threshold, for selection to the next generation
    protected boolean fitnessThreshold(AndroidUnitTestResultSet results, double orig) {

        return results.isPatchValid();
    }

    // Compare two fitness values, newFitness better if result > 0
    protected double compareFitness(double newFitness, double oldFitness) {

        return oldFitness - newFitness;
    }


    // Adds a random edit of the given type with equal probability among allowed types
    protected Patch mutate(Patch oldPatch) {
        Patch patch = oldPatch.clone();
        patch.addRandomEditOfClasses(rng, editTypes);
        return patch;
    }

    // Tournament selection for patches
    protected List<Patch> select(Map<Patch, Double> population, Patch origPatch, double origFitness) {

        List<Patch> patches = new ArrayList(population.keySet());
        if (patches.size() < indNumber) {
            population.put(origPatch, origFitness);
            while (patches.size() < indNumber) {
                patches.add(origPatch);
            }
        }
        List<Patch> selectedPatches = new ArrayList<>();

        // Pick half of the population size
        for (int i = 0; i < indNumber / 2; i++) {

            Collections.shuffle(patches, rng);

            // Best patch from x% randomly selected patches picked each time
            Patch bestPatch = patches.get(0);
            double best = population.get(bestPatch);
            for (int j = 1; j < (indNumber * tournamentPercentage); j++) {
                Patch patch = patches.get(j);
                double fitness = population.get(patch);

                if (compareFitness(fitness, best) > 0) {
                    bestPatch = patch;
                    best = fitness;
                }
            }

            selectedPatches.add(bestPatch.clone());

        }
        return selectedPatches;
    }

    // Uniform crossover: patch1patch2 and patch2patch1 created, each edit added with x% probability
    protected List<Patch> crossover(List<Patch> patches, Patch origPatch) {

        List<Patch> crossedPatches = new ArrayList<>();

        Collections.shuffle(patches, rng);
        int half = patches.size() / 2;
        for (int i = 0; i < half; i++) {

            Patch parent1 = patches.get(i);
            Patch parent2 = patches.get(i + half);
            List<Edit> list1 = parent1.getEdits();
            List<Edit> list2 = parent2.getEdits();

            Patch child1 = origPatch.clone();
            Patch child2 = origPatch.clone();

            for (int j = 0; j < list1.size(); j++) {
                if (rng.nextFloat() > mutateProbability) {
                    child1.add(list1.get(j));
                }
            }
            for (int j = 0; j < list2.size(); j++) {
                if (rng.nextFloat() > mutateProbability) {
                    child1.add(list2.get(j));
                }
                if (rng.nextFloat() > mutateProbability) {
                    child2.add(list2.get(j));
                }
            }
            for (int j = 0; j < list1.size(); j++) {
                if (rng.nextFloat() > mutateProbability) {
                    child2.add(list1.get(j));
                }
            }

            crossedPatches.add(parent1);
            crossedPatches.add(parent2);
            crossedPatches.add(child1);
            crossedPatches.add(child2);
        }

        return crossedPatches;
    }

    private AndroidUnitTestResultSet testPatch(Patch patch, boolean breakOnFail, int runs) {
        AndroidUnitTestResultSet result = new AndroidUnitTestResultSet(patch, false, new ArrayList<>());
        try {
            result = testRunner.runTests(patch, breakOnFail, runs);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }


    private AndroidUnitTestResultSet testPatchLocally(Patch patch, boolean breakOnFail, int runs) {
        AndroidUnitTestResultSet result = new AndroidUnitTestResultSet(patch, false, new ArrayList<>());
        try {
            result = testRunner.runTestsLocally(patch, runs, breakOnFail);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    private AndroidUnitTestResultSet testPatch(Patch patch) {
        return testPatch(patch, true, 5);
    }

    public void writeHeader() {
        String entry = "Gen, Ind, Patch, Valid, Fitness, Time\n";
        try {
            FileWriter writer = new FileWriter(outputFile, true);
            writer.write(entry);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    public void writePatch(Patch patch, AndroidUnitTestResultSet resultSet, int gen) {
        ZonedDateTime now = ZonedDateTime.now();
        now = now.withZoneSameInstant(ZoneId.of("UTC"));
        String entry =
                gen + ", " + patch.toString() + ", " +
                        Boolean.toString(resultSet.isPatchValid()) + ", " +
                        Double.toString(resultSet.getMemoryUsage()) + ", " + now.toString() + "\n";
        try {
            FileWriter writer = new FileWriter(outputFile, true);
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }




    public void evaluatePatch(String patchString) {
        Patch patch = getPatch(patchString);
        System.out.println(patchString);
        System.out.println(patch);
        try {
            for (int i =0; i< 10; i++) {

                AndroidUnitTestResultSet results = testPatchLocally(patch, false, 5);
                ZonedDateTime now = ZonedDateTime.now();
                FileWriter writer = new FileWriter("Validation.txt", true);
                String line = patchString + " " + results.getExecutionTime() + " " + now;
                writer.write(line);
                writer.write("\n");
                writer.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void getDiff(String patch, int i) {
        Patch origPatch = new Patch(sourceFile);
        String origString = origPatch.apply();
        Patch newPatch = getPatch(patch);
        String newString = newPatch.apply();
        try {
            FileUtils.writeStringToFile(new File("source.original"), origString, Charset.defaultCharset());
        } catch (IOException e) {
            Logger.error("Could not write original source.");
            Logger.trace(e);
            System.exit(-1);
        }


        try {
            FileUtils.writeStringToFile(new File("source.patched"), newString, Charset.defaultCharset());
        } catch (IOException ex) {
            Logger.error("Could not write patched source.");
            Logger.trace(ex);
            System.exit(-1);
        }

        try {
            String output = new ProcessExecutor().command("diff", "source.original", "source.patched", "--side-by-side")
                    .readOutput(true).execute()
                    .outputUTF8();
            String filename = "diffs/" + project.getProjectName() + i;
            try {
                FileUtils.writeStringToFile(new File(filename), patch.toString(), Charset.defaultCharset());
                FileUtils.writeStringToFile(new File(filename), output, Charset.defaultCharset());
            } catch (IOException e) {
                Logger.error("Could not write original source.");
                Logger.trace(e);
                System.exit(-1);
            }
        } catch (IOException ex) {
            Logger.trace(ex);
            System.exit(-1);
        } catch (InterruptedException ex) {
            Logger.trace(ex);
            System.exit(-1);
        } catch (TimeoutException ex) {
            Logger.trace(ex);
            System.exit(-1);
        }


    }

    private Patch getPatch(String patch) {
        Patch origPatch = new Patch(sourceFile);
        if(patch.replaceAll("\\s+","").equals("|")){
            return origPatch;
        }
        String[] edits = patch.split("\\|");
        for (String edit : edits) {
            if (edit.equals("")) {
                continue;
            }
            String[] editTokens = edit.split(" ");
            String cls = editTokens[1];
            edit = edit.substring(1);
            String[] sourceTokens;
            String[] destTokens;
            switch (cls) {
                case "gin.edit.statement.CopyStatement":
                    origPatch.add(CopyStatement.fromString(edit));
                    break;
                case "gin.edit.statement.DeleteStatement":
                    origPatch.add(DeleteStatement.fromString(edit));
                    break;
                case "gin.edit.statement.ReplaceStatement":
                    origPatch.add(ReplaceStatement.fromString(edit));
                    break;
                case "gin.edit.statement.MoveStatement":
                    origPatch.add(MoveStatement.fromString(edit));
                    break;
                case "gin.edit.statement.SwapStatement":
                    origPatch.add(SwapStatement.fromString(edit));
                    break;
                case "gin.edit.statement.CacheEdit":
                    origPatch.add(CacheEdit.fromString(edit));
                    break;
            }

        }
        return origPatch;
    }

    public void timeTests(){

        Patch patch = new Patch(sourceFile);
        long startTime = System.currentTimeMillis();
        try {
            AndroidUnitTestResultSet results = testRunner.runTests(patch, 10, false);
            long endTime = System.currentTimeMillis();
            System.out.println(startTime);
            System.out.println(endTime);
            Float avg = (endTime - startTime) / 10f;
            FileWriter writer = new FileWriter(new File("times.txt"), true);
            writer.write(avg.toString() + "\n");
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public void getJankiestTest(){
        writeHeader();
        Patch patch = new Patch(sourceFile);
        try {
                project.instrumentedTests = project.getAllInstrumentedTests();
                for(AndroidTest test : project.instrumentedTests){
                    test.setPerformance(true);
                }
                AndroidUnitTestResultSet results = testRunner.runTests(patch, false, 2);
                HashMap<String, Long> perTest = results.getAverageJankinessPerTest();
                for(String testName : perTest.keySet()){
                    writeJank(testName, perTest.get(testName), 0);
                }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }
    }



    public void writeJank(String testName, Long jank, int id){
        ZonedDateTime now = ZonedDateTime.now();
        now = now.withZoneSameInstant(ZoneId.of("UTC"));
        String entry =
                testName + ": " + jank.toString() + "\n";
        try {
            FileWriter writer = new FileWriter(outputFile, true);
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writeTest(AndroidTest test){
        String entry =
                test.fileName +  ": " + test.toString() + "\n";
        try {
            FileWriter writer = new FileWriter(outputFile, true);
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void testPatches(String patchFile){
        try {
            Scanner scan = new Scanner(new File(patchFile));
            while (scan.hasNextLine()) {
                String patchStr = scan.nextLine();
                evaluatePatch(patchStr);

            }
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public void getDiffs(String patchFile){
        try {
            Scanner scan = new Scanner(new File(patchFile));
            int i =0;
            while (scan.hasNextLine()) {
                String patchStr = scan.nextLine();
                System.out.println(patchStr);
                getDiff(patchStr, i);
                i += 1;

            }
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }


    public void profileNetwork(){
        for (int i = 0;i<30;i++) {
            ping();
        }
        System.out.println("sleeping");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Patch empty = new Patch(sourceFile);
        ArrayList<AndroidTest> tests = project.getAllLocalTests();

        project.unitTests = tests;
        for(int i=0; i<4; i++){
            AndroidUnitTestResultSet res = testPatchLocally(empty, false, 1);
            if (res.getNetworkUsage() > 0) {
                writePatch(empty, res, i);
            } else{
                break;
            }
        }
    }

    public void ping(){
        try {
            URL oracle = new URL("http://www.oracle.com/");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void timeLaunch(String patch){
        Patch newPatch = getPatch(patch);
        try {
            double time = testRunner.timeLaunch(newPatch);
            writeLaunch(patch, time);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(2);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(2);
        }

    }
    public void timeLaunches(String patchFile){
        try {
            Scanner scan = new Scanner(new File(patchFile));
            while (scan.hasNextLine()) {
                String patchStr = scan.nextLine();
                for (int i = 0; i<10; i++) {
                    timeLaunch(patchStr);
                }

            }
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public void writeLaunch(String patch, double time){
        String entry =
                patch +  "," + time + "\n";
        try {
            FileWriter writer = new FileWriter("launches.csv", true);
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
