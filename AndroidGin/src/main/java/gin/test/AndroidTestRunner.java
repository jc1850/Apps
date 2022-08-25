package gin.test;

import gin.Patch;
import gin.util.*;
import org.apache.commons.io.FileUtils;
import org.gradle.internal.time.Time;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndroidTestRunner {

    private AndroidDebugBridge adb;
    private  AndroidProject project;
    private List<AndroidTest> tests;
    private String apk;
    private String testapk;
    private String activity;
    private String fileName;
    private String testAppId;
    private String testRunner;
    private String appName;
    private AdbJankSampler adbJankSampler;
    private AdbMemorySampler adbMemorySampler;
    private localMemorySampler memorySampler;
    public String UID;


    public AndroidTestRunner(AndroidProject project, AndroidConfig config){

        String adbPath = config.getAdbPath();
        String deviceId = config.getDeviceName();
        testAppId = config.getTestAppName();
        adb = new AndroidDebugBridge(deviceId, testAppId, adbPath);
        //AndroidDebugBridge jankAdb = new AndroidDebugBridge(deviceId, testAppId, adbPath);
        //AndroidDebugBridge memAdb = new AndroidDebugBridge(deviceId, testAppId, adbPath);
        appName = config.getAppName();
        //adbJankSampler = new AdbJankSampler(jankAdb, appName, 10);
        //adbMemorySampler = new AdbMemorySampler(memAdb, appName, 25);
        memorySampler = new localMemorySampler();
        apk = config.getApkPath();
        fileName = config.getFilePath();
        this.project = project;
        testRunner = config.getTestRunner();
        String[] tokens= config.getTargetMethods().get(0).split("\\.");
        testapk= config.getTestApkPath();
        activity =String.join(".", Arrays.copyOfRange(tokens, 0, tokens.length-1));

        //getUID();
        memorySampler.start();
        //adbJankSampler.start();
       //adbMemorySampler.start();
        //testapk = config.getTestApkPath();
    }


    //install app, reset all gfxinfo
    public void setUp(){

    }

    private void getUID(){
        adb.runShellCommand("dumpsys package " + appName, true);
        String out = adb.output;
        for(String line : out.split("\r\n")){
            if (line.contains("userId=")){
                UID = line.split("=")[1];
            }
        }
    }

    public void installTestAPK() {
        adb.installApp(testapk);
    }

    public AndroidUnitTestResultSet runTests(Patch patch, int frames, boolean breakOnFail) throws IOException, InterruptedException {
        ArrayList<AndroidTestResult> results = new ArrayList<>();
        if(TestRunner.isPatchedSourceSame(patch.getSourceFile().getSource(), patch.apply())){
            if(!patch.toString().equals("|")) {
                return new AndroidUnitTestResultSet(patch, false, results);
            }
        }
        boolean passed;
        patch.writePatchedSourceToFile(fileName);
        System.out.println("compiling app");
        int buildExit = project.buildApp();
        patch.undoWrite(fileName);
        if (buildExit != 0){
            System.out.println("Failed to build App");
            return new AndroidUnitTestResultSet(patch, false, results);
        }

        project.runLocalTests((ArrayList<AndroidTest>) project.unitTests, 1);


        System.out.println("Installing App");
        boolean failed = false;
        adb.installApp(apk);
        for (AndroidTest test : project.instrumentedTests) {
            if(! test.isPerformance()) {
                AndroidTestResult result = runInstrumentedTest(test);
                results.add(result);
                passed = result.isPassed();
                if (!passed && breakOnFail) {
                    return new AndroidUnitTestResultSet(patch, false, results);
                }
            }
            else{
                int totalFrames = 0;
                int jankFrames = 0;
                int count = 0;
                passed = true;
                while (totalFrames < frames && count < 10){
                    AndroidTestResult result = runInstrumentedTest(test);
                    if(result.getJankiness().totalFrames > 1) {
                        totalFrames += result.getJankiness().totalFrames;
                        jankFrames += result.getJankiness().jankyFrames;

                        passed = passed && result.isPassed();
                        results.add(result);
                    }
                    count++;
                    System.out.println(totalFrames + "/" + frames + " frames generated so far");
                    System.out.println("Test run " + count + " times");
                    if (count == 5 && 5 == totalFrames){
                       break;
                    }
                    if (!passed && breakOnFail){
                        return new AndroidUnitTestResultSet(patch, false, results);
                    }
                }
            }

        }
        return new AndroidUnitTestResultSet(patch, true, results);
    }

    public AndroidUnitTestResultSet runTests(Patch patch, int frames) throws IOException, InterruptedException {
        return runTests(patch, frames, true);
    }

    public AndroidUnitTestResultSet runTests(Patch patch, boolean breakOnFail, int runs) throws IOException, InterruptedException {
        ArrayList<AndroidTestResult> results = new ArrayList<>();
        /*if(TestRunner.isPatchedSourceSame(patch.getSourceFile().getSource(), patch.apply())){
            if(!patch.toString().equals("|")) {
                return new AndroidUnitTestResultSet(patch, false, results);
            }
        }*/
        boolean passed;
        if (! (patch.getEdits().size() == 0)) {
            patch.writePatchedSourceToFile(fileName);

            System.out.println("compiling app");
            int buildExit = project.buildApp();
            patch.undoWrite(fileName);
            if (buildExit != 0) {
                System.out.println("Failed to build App");
                return new AndroidUnitTestResultSet(patch, false, results);
            }
        }
        if (! project.runLocalTests((ArrayList<AndroidTest>) project.unitTests, runs).isPassed()){
            return new AndroidUnitTestResultSet(patch, false, results);
        }


        //System.out.println("Installing App");
        boolean failed = false;
        adb.installApp(apk);
        for (AndroidTest test : project.instrumentedTests) {
            if (!test.isPerformance()) {
                AndroidTestResult result = runInstrumentedTest(test);
                results.add(result);
                passed = result.isPassed();
                if (!passed && breakOnFail) {
                    return new AndroidUnitTestResultSet(patch, false, results);
                }
            } else {
                continue;
            }
        }
        for (AndroidTest test : project.instrumentedTests) {
            if(test.isPerformance()) {
                int totalFrames = 0;
                int jankFrames = 0;
                int count = 0;
                int acCount = 0;
                passed = true;
                while (acCount < 1){
                    AndroidTestResult result = runInstrumentedTest(test);
                    if(result.getJankiness().totalFrames > 1) {
                        totalFrames += result.getJankiness().totalFrames;
                        jankFrames += result.getJankiness().jankyFrames;
                        System.out.println(result.getJankiness().jankyFrames + ", "+ result.getJankiness().totalFrames);
                        passed = passed && result.isPassed();
                        results.add(result);
                        count++;
                        acCount = 0;
                    }
                    acCount++;

                    System.out.println("Test run " + count + " times");
                    if (acCount == 5){
                        break;
                    }
                    if (!passed && breakOnFail){
                        return new AndroidUnitTestResultSet(patch, false, results);
                    }
                    if (!passed){
                       break;
                    }
                }
            }
            else{
                continue;
            }

        }
        return new AndroidUnitTestResultSet(patch, true, results);
    }

    public AndroidUnitTestResultSet runTestsLocally(Patch patch, int runs, boolean breakOnFail){
        ArrayList<AndroidTestResult> results = new ArrayList<>();
        if(TestRunner.isPatchedSourceSame(patch.getSourceFile().getSource(), patch.apply())) {
            if (!patch.toString().equals("|")) {
                return new AndroidUnitTestResultSet(patch, false, results);
            }
        }
        patch.writePatchedSourceToFile(fileName);
        if (project.buildUnitTests()!= 0){
            return new AndroidUnitTestResultSet(patch, false, results);
        }

        AndroidTestResult result = project.runLocalTests((ArrayList<AndroidTest>) project.unitTests, runs);
        if (! result.isPassed()) {
            results.add(result);
            System.out.println("test Failed");
            return new AndroidUnitTestResultSet(patch, false, results);
        }
        results.add(result);
        patch.undoWrite(fileName);
        return new AndroidUnitTestResultSet(patch, true, results);
    }



    public double timeLaunch(Patch patch) throws IOException, InterruptedException {
        ArrayList<AndroidTestResult> results = new ArrayList<>();
        if (!(patch.getEdits().size() == 0)) {
            patch.writePatchedSourceToFile(fileName);

            System.out.println("compiling app");
            int buildExit = project.buildApp();

            patch.undoWrite(fileName);
            if (buildExit != 0) {
                System.out.println("Failed to build App");
                return Long.MAX_VALUE;
            }
        }

        //System.out.println("Installing App");
        adb.installApp(apk);
        AndroidTestResult res =  runInstrumentedTest(project.instrumentedTests.get(0));
        return res.getCPUTime();
    }


    private NetStats getNetStats(){
        adb.runShellCommand("cat /proc/net/xt_qtaguid/stats", true);
        String out = adb.output;
        NetStats netStats = new NetStats(0,0);
        for (String line : out.split("\n")){
            String[] split = line.split(" ");
            if(split[3].equals(UID) && split[4].equals("0")){
                int Rx = Integer.parseInt(split[5]);
                netStats.Rx = Rx;
                int Tx = Integer.parseInt(split[7]);
                netStats.Tx = Tx;
                return netStats;
            }
        }
        return  netStats;

    }


    private class NetStats{
        public int Rx;
        public int Tx;

        public NetStats(int Rx, int Tx){
            this.Rx = Rx;
            this.Tx = Tx;

        }

        public void subtract(NetStats netStats){
            this.Rx -= netStats.Rx;
            this.Tx -= netStats.Tx;
        }

        @Override
        public String toString(){
            return "Rx: " + Rx + " Tx: " + Tx;
        }
    }

    public static boolean parseResult(String result){
        for(String line : result.split("\n")){
            if (line.startsWith("OK ")){
                return true;
            }
        }
        return false;
    }

    public void stop(){
        memorySampler.stop();
        adbJankSampler.stop();
        //adbMemorySampler.stop();
    }


    public AndroidTestResult runInstrumentedTest(AndroidTest test){
        //adbMemorySampler.resetStats();
        System.out.println("Running test: " + test);
        String cmd = "am instrument -w  --no_window_animation -e class " + test.getModuleName()
                + "." + test.getFullClassName() + "#" + test.getMethodName() +
                " " + testAppId + "/" + testRunner;
        System.out.println(cmd);
        long startTime = System.currentTimeMillis();
        adb.runShellCommand(cmd, true);
        long endTime = System.currentTimeMillis();
        String out = adb.output;
        boolean passed = parseResult(out);
        System.out.println(passed);

        //TestExecutionMemory executionMemory = adbMemorySampler.getExecutionMemory();
        AndroidTestResult result = new AndroidTestResult(test, 1);
        //result.setExecutionMemory(executionMemory.medianReading().longValue());
        result.setPassed(passed);
        result.setCPUTime(endTime-startTime);
        return result;
    }

    public AndroidTestResult runUnitTest(AndroidTest test){
        int passed = project.runLocaltest(test);
        AndroidTestResult result = new AndroidTestResult(test, 1);
        result.setPassed(passed==0);
        return result;
    }


    public JankyFrames monkeyRun(Patch patch, int inputs){
        adbJankSampler.resetStats();
        //adbMemorySampler.resetStats();
        patch.writePatchedSourceToFile(fileName);
        System.out.println("compiling app");
        int buildExit = project.buildApp();
        patch.undoWrite(fileName);
        if (buildExit != 0){
            System.out.println("Failed to build App");
            return null;
        }
        System.out.println("Installing App");
        adb.installApp(apk);
        String cmd = "monkey -p "+ appName + " -c android.intent.category.MONKEY --throttle 100 -s 111 5000";
        adb.runShellCommand(cmd);
        JankyFrames jankyFrames = adbJankSampler.getJankyFrames();
        return  jankyFrames;
    }



}
