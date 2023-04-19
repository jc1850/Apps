package de.storchp.fdroidbuildstatus;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.google.android.material.color.DynamicColors;

import de.storchp.fdroidbuildstatus.adapter.db.DbAdapter;
import de.storchp.fdroidbuildstatus.adapter.fdroid.FdroidClient;
import de.storchp.fdroidbuildstatus.adapter.gitlab.GitlabClient;
import de.storchp.fdroidbuildstatus.monitor.MonitorJobService;
import de.storchp.fdroidbuildstatus.utils.ExceptionHandler;
import de.storchp.fdroidbuildstatus.utils.NotificationUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class BaseApplication extends Application {

    private FdroidClient fdroidAPI;
    private GitlabClient gitlabClient;
    private DbAdapter dbAdapter;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // handle crashes only outside the crash reporter activity/process
        if (!isCrashReportingProcess()) {
            Thread.setDefaultUncaughtExceptionHandler(
                    new ExceptionHandler(this, Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    private boolean isCrashReportingProcess() {
        var processName = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            // Using the same technique as Application.getProcessName() for older devices
            // Using reflection since ActivityThread is an internal API
            try {
                @SuppressLint("PrivateApi") var activityThread = Class.forName("android.app.ActivityThread");
                @SuppressLint("DiscouragedPrivateApi") var getProcessName = activityThread.getDeclaredMethod("currentProcessName");
                processName = (String) getProcessName.invoke(null);
            } catch (Exception ignored) {
            }
        } else {
            processName = Application.getProcessName();
        }
        return processName != null && processName.endsWith(":crash");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        fdroidAPI = new FdroidClient("https://f-droid.org");
        gitlabClient = new GitlabClient("https://gitlab.com");

        NotificationUtils.createNotificationChannel(this);
        MonitorJobService.schedule(this);
        PreferenceUtils.applyDefaultNightMode(this);
    }

    public DbAdapter getDbAdapter() {
        return dbAdapter;
    }

    public FdroidClient getFdroidClient() {
        return fdroidAPI;
    }

    public GitlabClient getGitlabClient() {
        return gitlabClient;
    }

    public long getLastUpdate() {
        return PreferenceUtils.getLastUpdateLoaded(this);
    }

    public void setLastUpdate() {
        PreferenceUtils.setLastUpdateLoaded(this, System.currentTimeMillis());
    }
}
