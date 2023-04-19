package de.storchp.fdroidbuildstatus.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG = "StartupReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            MonitorJobService.schedule(context);
        } else {
            Log.d(TAG, "received unsupported Intent " + intent);
        }
    }

}
