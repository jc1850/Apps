package de.storchp.fdroidbuildstatus.monitor;

import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.Map;
import java.util.Objects;

import de.storchp.fdroidbuildstatus.BaseApplication;
import de.storchp.fdroidbuildstatus.R;
import de.storchp.fdroidbuildstatus.adapter.db.DbAdapter;
import de.storchp.fdroidbuildstatus.adapter.fdroid.BuildResult;
import de.storchp.fdroidbuildstatus.model.App;
import de.storchp.fdroidbuildstatus.model.AppNotification;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.utils.NotificationUtils;
import de.storchp.fdroidbuildstatus.utils.PreferenceUtils;

public class MonitorJobService extends JobService {

    private static final String TAG = MonitorJobService.class.getSimpleName();

    private static final int JOB_ID = 1000;

    public static void schedule(Context context) {
        var jobScheduler = ContextCompat.getSystemService(context, JobScheduler.class);
        assert jobScheduler != null;
        jobScheduler.cancel(JOB_ID); // cancel previous scheduled job

        if (!PreferenceUtils.isUpdateCheckEnabled(context)) {
            Log.i(TAG, "MonitorService disabled");
            return;
        }
        if (!NotificationUtils.areNotificationsEnabled(context)) {
            Log.i(TAG, "Notifications diabled, disable MonitorService");
            PreferenceUtils.setUpdateCheckEnabled(context, false);
            return;
        }

        var builder = new JobInfo.Builder(JOB_ID, new ComponentName(context, MonitorJobService.class));
        var updateInterval = PreferenceUtils.getUpdateInterval(context);
        builder.setPeriodic(updateInterval)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            builder.setRequiresBatteryNotLow(true);
        }
        jobScheduler.schedule(builder.build());
        Log.i(TAG, "MonitorService scheduled for " + updateInterval + " millis interval");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "MonitorService job started");
        var baseApplication = (BaseApplication) getApplication();

        var buildRunType = PreferenceUtils.getBuildCycleCheck(this);
        if (buildRunType == BuildCycle.BUILD) {
            baseApplication.getFdroidClient().getBuild(response -> {
                Log.d(TAG, "build response: " + response.isSuccess());
                if (response.isSuccess()) {
                    processBuildRun(response.value(), baseApplication.getDbAdapter(), buildRunType);
                } else {
                    Log.e(TAG, "MonitorService failed: " + response.errorMessage());
                }
                jobFinished(params, false);
            });
        } else if (buildRunType == BuildCycle.RUNNING) {
            baseApplication.getFdroidClient().getRunning(response -> {
                Log.d(TAG, "running response: " + response.isSuccess());
                if (response.isSuccess()) {
                    var result = response.value();
                    if (result instanceof BuildResult) {
                        var buildResult = (BuildResult) result;
                        processBuildRun(buildResult, baseApplication.getDbAdapter(), buildRunType);
                    }
                } else {
                    Log.e(TAG, "MonitorService failed: " + response.errorMessage());
                }
                jobFinished(params, false);
            });
        }

        return true;
    }

    private void processBuildRun(BuildResult buildRun, DbAdapter dbAdapter, BuildCycle buildCycle) {
        var oldBuildRun = dbAdapter.loadBuildRuns().get(buildCycle);
        Log.d(TAG, "oldBuild Last-Modified: " + (oldBuildRun != null ? oldBuildRun.getLastModified() : "") + ", newBuildRun Last-Modified: " + buildRun.getLastModified());
        if (oldBuildRun == null || !oldBuildRun.getLastModified().equals(buildRun.getLastModified())) {
            dbAdapter.saveBuildRun(buildRun, buildCycle);
            var favourites = dbAdapter.getFavourites();
            if (PreferenceUtils.isNotifyFavouritesOnly(MonitorJobService.this)) {
                notifyFavourites(buildRun, dbAdapter, buildCycle, favourites);
            } else {
                NotificationUtils.createNewBuildNotification(MonitorJobService.this, getString(R.string.new_build_notification));
            }
        }
    }

    private void notifyFavourites(final BuildResult buildRun, final DbAdapter dbAdapter, final BuildCycle buildCycle, final Map<String, App> favourites) {
        var notifiedApps = dbAdapter.getNotificationsFor(buildCycle, buildRun.getStartTimestamp());
        var newFavoriteApps = buildRun.getAllBuilds().stream()
                .map(i -> new AppNotification(i.getId(), i.getVersionCode()))
                .filter(a -> favourites.containsKey(a.getId()) && !notifiedApps.contains(a))
                .collect(toSet());
        if (!newFavoriteApps.isEmpty()) {
            if (newFavoriteApps.size() <= 3) {
                var names = newFavoriteApps.stream()
                        .map(a -> favourites.get(a.getId()))
                        .filter(Objects::nonNull)
                        .map(App::getDisplayName)
                        .collect(joining(", "));
                NotificationUtils.createNewBuildNotification(MonitorJobService.this, getString(R.string.new_build_notification_fav_names, names));
            } else {
                NotificationUtils.createNewBuildNotification(MonitorJobService.this, getString(R.string.new_build_notification_favs, newFavoriteApps.size()));
            }
            notifiedApps.addAll(newFavoriteApps);
            dbAdapter.saveNotifications(buildCycle, buildRun.getStartTimestamp(), notifiedApps);
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
