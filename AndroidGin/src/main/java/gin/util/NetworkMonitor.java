package gin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.swing.*;

import static com.google.common.primitives.Floats.max;
import static java.lang.Float.sum;

public class NetworkMonitor implements Runnable{
    private Process p;
    private long pid;
    private BufferedReader stdin;
    private Thread t;
    private boolean running;
    private float sent;
    private float received;
    private HashMap<String, Float> usageMap;

    public NetworkMonitor(){
        usageMap = new HashMap<>();
        pid = -1;
    }
    public void startMonitor(){
        if(p != null){
            return;
        }
        String cmd = "nethogs -v 2 -d 0.01 -t";
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
            pb.environment().putAll(System.getenv());
            pb.redirectErrorStream(true);
            //pb.inheritIO();
            p= pb.start();
            stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void setPid(long pid){
        this.pid = pid;
    }
    public void stopMonitor(){
        if(p != null){
            p.destroy();
            p = null;
        }
    }

    public int  updateStats(){
            try {
                while (stdin.ready()) {
                    String line =stdin.readLine();
                    String[] tokens = line.split("\t");
                    String linePid = tokens[0].split("/")[1];
                    if (linePid.equals(Long.valueOf(pid).toString()) || linePid.equals("0")|| tokens[0].contains("java")){
                        Float traffic = Float.valueOf(tokens[1]);
                        traffic += Float.valueOf(tokens[2]);
                        usageMap.put(tokens[0], traffic);
                    }
                }

                }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ArrayIndexOutOfBoundsException e){

            }
        return 0;
    }

    public float totalUsage(){
        return usageMap.values().stream().reduce(0f, Float::sum);
    }

    public void resetStats(){
        sent = 0;
        received = 0;
    }

    private boolean checkChild(String childPid){
        String cmd = "pstree -sp " + childPid;
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
            pb.environment().putAll(System.getenv());
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            while (p.isAlive()) {
                if ((line = stdin.readLine()) != null) {
                    if (line.contains(Long.valueOf(pid).toString())){
                        return true;
                    }
                }
                p.waitFor();
                return false;
            }

        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }


    public void run(){
        while(running){
            try {
                updateStats();
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    public void start(){
        if (t==null){
            t = new Thread(this, "Sampler");
            running = true;
            t.start();
        }
    }

    public void stop(){
        if (running){
            t.stop();
            running = false;
        }
    }
}
