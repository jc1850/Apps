package com.aaronjwood.portauthority.runnable;


import android.widget.Button;

import com.aaronjwood.portauthority.R;
import com.aaronjwood.portauthority.activity.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
public class ScanHostsRunnableTest {

    @Test (timeout=1000)
    public void test1() throws InterruptedException {
        ArrayList<String> a = new ArrayList<>();

        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.setupHostDiscovery();
        ((Button) activity.findViewById(R.id.discoverHosts)).performClick();
//        assert activity.scanProgressDialog.getProgress()  == 0;
        String[] ipParts = new String[]{"127","0","0","0"};
        Thread running = new Thread(new ScanHostsRunnable(ipParts, 1, 255, activity));
        running.start();
        while (running.isAlive()){

        }
        while (activity.scanProgressDialog.getProgress() < 255) {

        }
        System.out.println("\nGin Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));


    }

}