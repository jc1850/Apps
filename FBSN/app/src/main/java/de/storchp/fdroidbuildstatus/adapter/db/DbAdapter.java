package de.storchp.fdroidbuildstatus.adapter.db;

import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_APPS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_BUILDS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_BUILD_RUNS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_NOTIFICATIONS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_VERSIONS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.storchp.fdroidbuildstatus.adapter.fdroid.BuildItem;
import de.storchp.fdroidbuildstatus.adapter.fdroid.Index;
import de.storchp.fdroidbuildstatus.adapter.fdroid.RunningResult;
import de.storchp.fdroidbuildstatus.adapter.fdroid.UpdateResult;
import de.storchp.fdroidbuildstatus.adapter.gitlab.Metadata;
import de.storchp.fdroidbuildstatus.model.App;
import de.storchp.fdroidbuildstatus.model.AppBuild;
import de.storchp.fdroidbuildstatus.model.AppNotification;
import de.storchp.fdroidbuildstatus.model.BuildCycle;
import de.storchp.fdroidbuildstatus.model.BuildRun;
import de.storchp.fdroidbuildstatus.model.BuildStatus;
import de.storchp.fdroidbuildstatus.model.StatusFilter;

public class DbAdapter {

    private static final String TAG = DbAdapter.class.getSimpleName();

    private final Context context;
    private DbOpenHelper dbHelper;
    private SQLiteDatabase db;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new DbOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    public void toggleFavourite(String id) {
        try (Cursor cursor = db.query(DATABASE_TABLE_APPS, null, DbConstants.APPS.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null)) {
            ContentValues values = new ContentValues();
            if (cursor.moveToFirst()) {
                int fav = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.FAVOURITE));
                values.put(DbConstants.APPS.FAVOURITE, fav == 0 ? 1 : 0);
                db.update(DATABASE_TABLE_APPS, values, DbConstants.APPS.ID + " = ?", new String[]{id});
                return;
            }
            values.put(DbConstants.APPS.ID, id);
            values.put(DbConstants.APPS.FAVOURITE, 1);
            db.insert(DATABASE_TABLE_APPS, null, values);
        }
    }

    @NonNull
    public Map<String, de.storchp.fdroidbuildstatus.model.App> getFavourites() {
        var apps = new HashMap<String, de.storchp.fdroidbuildstatus.model.App>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_APPS + " WHERE " + DbConstants.APPS.FAVOURITE + " = 1", null)) {
            while (cursor.moveToNext()) {
                var app = createAppFromCursor(cursor);
                apps.put(app.getId(), app);
            }
        }
        return apps;
    }

    @NonNull
    private de.storchp.fdroidbuildstatus.model.App createAppFromCursor(@NonNull Cursor cursor) {
        return new de.storchp.fdroidbuildstatus.model.App(
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.NAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.FAVOURITE)) == 1,
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.SOURCE_CODE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.DISABLED)) == 1,
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NEEDS_UPDATE)) == 1,
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.ARCHIVED)) == 1,
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_PACKAGES)) == 1,
                cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_UPDATE_CHECK)) == 1
        );
    }

    public void updateApps(Collection<Index.App> apps) {
        for (Index.App app : apps) {
            try (Cursor cursor = db.query(DATABASE_TABLE_APPS, null, DbConstants.APPS.ID + "=?",
                    new String[]{String.valueOf(app.getPackageName())}, null, null, null)) {
                var values = new ContentValues();
                if (cursor.moveToFirst()) {
                    values.put(DbConstants.APPS.NAME, app.getAppName());
                    if (app.getSourceCode() != null) {
                        values.put(DbConstants.APPS.SOURCE_CODE, app.getSourceCode());
                    }
                    db.update(DATABASE_TABLE_APPS, values, DbConstants.APPS.ID + " = ?", new String[]{app.getPackageName()});
                } else {
                    values.put(DbConstants.APPS.ID, app.getPackageName());
                    values.put(DbConstants.APPS.NAME, app.getAppName());
                    values.put(DbConstants.APPS.SOURCE_CODE, app.getSourceCode());
                    db.insert(DATABASE_TABLE_APPS, null, values);
                }
            }
        }
    }

    public void updateApp(String id, Metadata metadata) {
        try (Cursor cursor = db.query(DATABASE_TABLE_APPS, null, DbConstants.APPS.ID + "=?",
                new String[]{id}, null, null, null)) {
            var values = new ContentValues();
            if (cursor.moveToFirst()) {
                values.put(DbConstants.APPS.NAME, metadata.getAppName());
                if (metadata.getSourceCode() != null) {
                    values.put(DbConstants.APPS.SOURCE_CODE, metadata.getSourceCode());
                }
                db.update(DATABASE_TABLE_APPS, values, DbConstants.APPS.ID + " = ?", new String[]{id});
            } else {
                values.put(DbConstants.APPS.ID, id);
                values.put(DbConstants.APPS.NAME, metadata.getAppName());
                values.put(DbConstants.APPS.SOURCE_CODE, metadata.getSourceCode());
                db.insert(DATABASE_TABLE_APPS, null, values);
            }
        }
        metadata.getBuilds().forEach(build -> upsertVersion(id, build.getVersionCode(), build.getVersionName()));
    }

    private void upsertVersion(String id, String versionCode, String versionName) {
        try (Cursor cursor = db.query(DATABASE_TABLE_VERSIONS, null,
                DbConstants.BUILDS.ID + " = ? AND " + DbConstants.BUILDS.VERSION_CODE + " = ?",
                new String[]{id, versionCode}, null, null, null)) {
            var values = new ContentValues();
            if (cursor.moveToFirst()) {
                values.put(DbConstants.VERSIONS.VERSION_NAME, versionName);
                db.update(DATABASE_TABLE_VERSIONS, values, DbConstants.BUILDS.ID + " = ? AND " + DbConstants.BUILDS.VERSION_CODE + " = ?", new String[]{id, versionCode});
            } else {
                values.put(DbConstants.VERSIONS.ID, id);
                values.put(DbConstants.VERSIONS.VERSION_CODE, versionCode);
                values.put(DbConstants.VERSIONS.VERSION_NAME, versionName);
                db.insert(DATABASE_TABLE_VERSIONS, null, values);
            }
        }
    }

    public void saveBuildRun(RunningResult buildRun, BuildCycle buildCycle) {
        Log.i(TAG, "Save new buildRun " + buildCycle + " " + buildRun.getLastModified());
        db.beginTransaction();

        db.delete(DATABASE_TABLE_BUILD_RUNS, DbConstants.BUILD_RUNS.BUILD_RUN_TYPE + " = ?", new String[]{buildCycle.name()});
        var valuesBuildRun = new ContentValues();
        valuesBuildRun.put(DbConstants.BUILD_RUNS.BUILD_RUN_TYPE, buildCycle.name());
        valuesBuildRun.put(DbConstants.BUILD_RUNS.START, buildRun.getStartTimestamp());
        valuesBuildRun.put(DbConstants.BUILD_RUNS.END, buildRun.getEndTimestamp());
        valuesBuildRun.put(DbConstants.BUILD_RUNS.LAST_MODIFIED, buildRun.getLastModified() != null ? buildRun.getLastModified().getTime() : null);
        valuesBuildRun.put(DbConstants.BUILD_RUNS.LAST_UPDATED, System.currentTimeMillis());
        valuesBuildRun.put(DbConstants.BUILD_RUNS.MAX_BUILD_TIME_REACHED, buildRun.getMaxBuildTimeReached() ? 1 : 0);
        valuesBuildRun.put(DbConstants.BUILD_RUNS.SUBCOMMAND, buildRun.getSubcommand());
        valuesBuildRun.put(DbConstants.BUILD_RUNS.DATA_COMMIT_ID, buildRun.getFdroiddata().getCommitId());
        db.insert(DATABASE_TABLE_BUILD_RUNS, null, valuesBuildRun);

        db.delete(DATABASE_TABLE_BUILDS, DbConstants.BUILDS.BUILD_RUN_TYPE + " = ?", new String[]{buildCycle.name()});
        for (BuildItem item : buildRun.getBuildItems()) {
            item.getId();
            var valuesBuild = new ContentValues();
            valuesBuild.put(DbConstants.BUILDS.BUILD_RUN_TYPE, buildCycle.name());
            valuesBuild.put(DbConstants.BUILDS.ID, item.getId());
            valuesBuild.put(DbConstants.BUILDS.VERSION_CODE, item.getVersionCode());
            valuesBuild.put(DbConstants.BUILDS.STATUS, item.getStatus().name());
            db.insert(DATABASE_TABLE_BUILDS, null, valuesBuild);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @NonNull
    public Map<BuildCycle, BuildRun> loadBuildRuns() {
        var buildRuns = new HashMap<BuildCycle, BuildRun>();

        try (Cursor cursorBuildRuns = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_BUILD_RUNS, new String[]{})) {
            while (cursorBuildRuns.moveToNext()) {
                var buildRun = createBuildRunFromCursor(cursorBuildRuns);
                if (buildRun != null) {
                    buildRuns.put(buildRun.getBuildCycle(), buildRun);
                    try (Cursor cursorBuildCount = db.rawQuery("SELECT " + DbConstants.BUILDS.STATUS + ", COUNT(*) AS " + DbConstants.COUNTER + " FROM " + DATABASE_TABLE_BUILDS + " WHERE " + DbConstants.BUILDS.BUILD_RUN_TYPE + " = ? GROUP BY " + DbConstants.BUILDS.STATUS, new String[]{buildRun.getBuildCycle().name()})) {
                        while (cursorBuildCount.moveToNext()) {
                            int count = cursorBuildCount.getInt(cursorBuildCount.getColumnIndexOrThrow(DbConstants.COUNTER));
                            switch (BuildStatus.valueOf(cursorBuildCount.getString(cursorBuildCount.getColumnIndexOrThrow(DbConstants.BUILDS.STATUS)))) {
                                case SUCCESS:
                                    buildRun.setSuccessCount(count);
                                    break;
                                case MISSING:
                                case FAILED:
                                    buildRun.setFailedCount(count);
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return buildRuns;
    }

    public List<de.storchp.fdroidbuildstatus.model.App> loadAppBuilds(BuildCycle buildCycle, boolean includeFavorites, StatusFilter statusFilter) {
        Map<String, de.storchp.fdroidbuildstatus.model.App> favourites = includeFavorites ? getFavourites() : Collections.emptyMap();
        var apps = new ArrayList<de.storchp.fdroidbuildstatus.model.App>();
        var appFilter = "";
        if (statusFilter == StatusFilter.DISABLED) {
            appFilter = " AND a." + DbConstants.APPS.DISABLED + " = 1";
        } else if (statusFilter == StatusFilter.ARCHIVED) {
            appFilter = " AND a." + DbConstants.APPS.ARCHIVED + " = 1";
        } else if (statusFilter == StatusFilter.NEEDS_UPDATE) {
            appFilter = " AND a." + DbConstants.APPS.NEEDS_UPDATE + " = 1";
        } else if (statusFilter == StatusFilter.NO_PACKAGES) {
            appFilter = " AND a." + DbConstants.APPS.NO_PACKAGES + " = 1";
        } else if (statusFilter == StatusFilter.NO_UPDATE_CHECK) {
            appFilter = " AND a." + DbConstants.APPS.NO_UPDATE_CHECK + " = 1";
        }
        var buildRunTypeFilter = "";
        if (buildCycle == BuildCycle.UPDATE) {
            buildRunTypeFilter = " AND ( b." + DbConstants.BUILDS.BUILD_RUN_TYPE + " = '" + buildCycle.name() + "'";
            buildRunTypeFilter += " OR  a." + DbConstants.APPS.DISABLED + " = 1";
            buildRunTypeFilter += " OR  a." + DbConstants.APPS.ARCHIVED + " = 1";
            buildRunTypeFilter += " OR  a." + DbConstants.APPS.NEEDS_UPDATE + " = 1";
            buildRunTypeFilter += " OR  a." + DbConstants.APPS.NO_PACKAGES + " = 1";
            buildRunTypeFilter += " OR  a." + DbConstants.APPS.NO_UPDATE_CHECK + " = 1 )";
        } else if (buildCycle != BuildCycle.NONE) {
            buildRunTypeFilter = " AND b." + DbConstants.BUILDS.BUILD_RUN_TYPE + " = '" + buildCycle.name() + "'";
        }
        var favFilter = "";
        if (includeFavorites) {
            favFilter = " AND a." + DbConstants.APPS.FAVOURITE + " = 1";
        }
        try (Cursor cursor = db.rawQuery("SELECT b." + DbConstants.BUILDS.BUILD_RUN_TYPE
                + ", a." + DbConstants.APPS.ID
                + ", b." + DbConstants.BUILDS.STATUS
                + ", v." + DbConstants.VERSIONS.VERSION_NAME
                + ", b." + DbConstants.BUILDS.VERSION_CODE
                + ", a." + DbConstants.APPS.SOURCE_CODE
                + ", a." + DbConstants.APPS.NAME
                + ", a." + DbConstants.APPS.FAVOURITE
                + ", r." + DbConstants.BUILD_RUNS.DATA_COMMIT_ID
                + ", a." + DbConstants.APPS.DISABLED
                + ", a." + DbConstants.APPS.NEEDS_UPDATE
                + ", a." + DbConstants.APPS.ARCHIVED
                + ", a." + DbConstants.APPS.NO_PACKAGES
                + ", a." + DbConstants.APPS.NO_UPDATE_CHECK
                + " FROM " + DATABASE_TABLE_APPS + " a "
                + " LEFT JOIN " + DATABASE_TABLE_BUILDS + " b ON b." + DbConstants.BUILDS.ID + " = a." + DbConstants.APPS.ID
                + " LEFT JOIN " + DATABASE_TABLE_VERSIONS + " v ON b." + DbConstants.BUILDS.ID + " = v." + DbConstants.VERSIONS.ID + " AND b." + DbConstants.BUILDS.VERSION_CODE + " = v." + DbConstants.VERSIONS.VERSION_CODE
                + " LEFT JOIN " + DATABASE_TABLE_BUILD_RUNS + " r ON r." + DbConstants.BUILD_RUNS.BUILD_RUN_TYPE + " = b." + DbConstants.BUILD_RUNS.BUILD_RUN_TYPE
                + " WHERE 1 = 1 "
                + buildRunTypeFilter
                + appFilter
                + favFilter
                + " ORDER BY b." + DbConstants.BUILDS.ID, new String[]{})) {
            de.storchp.fdroidbuildstatus.model.App app = null;
            while (cursor.moveToNext()) {
                var id = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.ID));
                if (app == null || !app.getId().equals(id)) {
                    app = new de.storchp.fdroidbuildstatus.model.App(
                            id,
                            cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.NAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.FAVOURITE)) == 1,
                            cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.SOURCE_CODE)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.DISABLED)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NEEDS_UPDATE)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.ARCHIVED)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_PACKAGES)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_UPDATE_CHECK)) == 1
                    );
                }
                var appBuild = createAppBuildFromCursor(cursor);
                if (appBuild != null && (!statusFilter.isBuildFilter()
                        || (statusFilter == StatusFilter.SUCCESSFUL_BUILD && appBuild.getStatus() == BuildStatus.SUCCESS)
                        || (statusFilter == StatusFilter.FAILED_BUILD && appBuild.getStatus() == BuildStatus.FAILED)
                        || (statusFilter == StatusFilter.MISSING_BUILD && appBuild.getStatus() == BuildStatus.MISSING))) {
                    app.getAppBuilds().add(appBuild);
                }

                if (apps.contains(app)) {
                    continue;
                }
                if (statusFilter.isBuildFilter() && app.getAppBuilds().isEmpty()) {
                    continue;
                }
                if (!statusFilter.isAppFilter() && app.getAppBuilds().isEmpty() && !includeFavorites && app.hasNoBuildStatusProperties()) {
                    continue;
                }

                apps.add(app);
                favourites.remove(app.getId());
            }
        }

        // include rest of the favorites if no other filter is set
        if (statusFilter == StatusFilter.NONE && buildCycle == BuildCycle.NONE) {
            apps.addAll(favourites.values());
        }

        return apps;
    }

    public de.storchp.fdroidbuildstatus.model.App loadAppBuilds(String id) {
        if (id == null) {
            return null;
        }
        de.storchp.fdroidbuildstatus.model.App app = null;
        var selectionArgs = new ArrayList<String>();
        selectionArgs.add(id);

        try (Cursor cursor = db.rawQuery("SELECT b." + DbConstants.BUILDS.BUILD_RUN_TYPE
                        + ", b." + DbConstants.BUILDS.ID
                        + ", b." + DbConstants.BUILDS.STATUS
                        + ", v." + DbConstants.VERSIONS.VERSION_NAME
                        + ", b." + DbConstants.BUILDS.VERSION_CODE
                        + ", a." + DbConstants.APPS.SOURCE_CODE
                        + ", a." + DbConstants.APPS.NAME
                        + ", a." + DbConstants.APPS.FAVOURITE
                        + ", r." + DbConstants.BUILD_RUNS.DATA_COMMIT_ID
                        + ", a." + DbConstants.APPS.DISABLED
                        + ", a." + DbConstants.APPS.NEEDS_UPDATE
                        + ", a." + DbConstants.APPS.ARCHIVED
                        + ", a." + DbConstants.APPS.NO_PACKAGES
                        + ", a." + DbConstants.APPS.NO_UPDATE_CHECK
                        + " FROM " + DATABASE_TABLE_APPS + " a "
                        + " LEFT JOIN " + DATABASE_TABLE_BUILDS + " b ON b." + DbConstants.BUILDS.ID + " = a." + DbConstants.APPS.ID
                        + " LEFT JOIN " + DATABASE_TABLE_VERSIONS + " v ON b." + DbConstants.BUILDS.ID + " = v." + DbConstants.VERSIONS.ID + " AND b." + DbConstants.BUILDS.VERSION_CODE + " = v." + DbConstants.VERSIONS.VERSION_CODE
                        + " LEFT JOIN " + DATABASE_TABLE_BUILD_RUNS + " r ON r." + DbConstants.BUILD_RUNS.BUILD_RUN_TYPE + " = b." + DbConstants.BUILD_RUNS.BUILD_RUN_TYPE
                        + " WHERE b." + DbConstants.BUILDS.ID + " = ?"
                , selectionArgs.toArray(new String[0]))) {
            while (cursor.moveToNext()) {
                if (app == null) {
                    app = new de.storchp.fdroidbuildstatus.model.App(
                            cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILDS.ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.NAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.FAVOURITE)) == 1,
                            cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.APPS.SOURCE_CODE)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.DISABLED)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NEEDS_UPDATE)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.ARCHIVED)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_PACKAGES)) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.APPS.NO_UPDATE_CHECK)) == 1);
                }

                var appBuild = createAppBuildFromCursor(cursor);
                if (appBuild != null) {
                    app.getAppBuilds().add(appBuild);
                }
            }
        }
        return app;
    }

    private String[] toArray(Set<BuildCycle> buildCycles) {
        return buildCycles.stream()
                .map(BuildCycle::name)
                .toArray(String[]::new);
    }

    private String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            return String.join(",", Collections.nCopies(len, "?"));
        }
    }

    private AppBuild createAppBuildFromCursor(Cursor cursor) {
        var buildRunType = getBuildRunTypeFromCursor(cursor);
        if (buildRunType == null) {
            return null;
        }
        return new AppBuild(
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILDS.VERSION_CODE)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.VERSIONS.VERSION_NAME)),
                BuildCycle.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILDS.BUILD_RUN_TYPE))),
                BuildStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILDS.STATUS))),
                cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.DATA_COMMIT_ID)));
    }

    private BuildRun createBuildRunFromCursor(Cursor cursor) {
        var buildRunType = getBuildRunTypeFromCursor(cursor);
        if (buildRunType == null) {
            return null;
        }
        var buildRun = new BuildRun();
        buildRun.setBuildCycle(buildRunType);
        buildRun.setStartTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.START)));
        buildRun.setEndTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.END)));
        buildRun.setLastModified(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.LAST_MODIFIED))));
        buildRun.setLastUpdated(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.LAST_UPDATED))));
        buildRun.setMaxBuildTimeReached(cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.MAX_BUILD_TIME_REACHED)) == 1);
        buildRun.setSubcommand(cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.SUBCOMMAND)));
        return buildRun;
    }

    @Nullable
    private BuildCycle getBuildRunTypeFromCursor(final Cursor cursor) {
        try {
            return BuildCycle.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.BUILD_RUNS.BUILD_RUN_TYPE)));
        } catch (Exception ignored) {
        }
        return null;
    }

    public void saveUpdate(UpdateResult updateResult, boolean running) {
        Log.i(TAG, "Save new update " + updateResult.getLastModified());
        db.beginTransaction();

        updateAppFeature(DbConstants.APPS.DISABLED, running, updateResult.getDisabled());
        updateAppFeature(DbConstants.APPS.NEEDS_UPDATE, running, updateResult.getNeedsUpdate());
        updateAppFeature(DbConstants.APPS.ARCHIVED, running, updateResult.getArchivePolicy0());
        updateAppFeature(DbConstants.APPS.NO_PACKAGES, running, updateResult.getNoPackages());
        updateAppFeature(DbConstants.APPS.NO_UPDATE_CHECK, running, updateResult.getNoUpdateCheck());

        saveBuildRun(updateResult, running ? BuildCycle.RUNNING : BuildCycle.UPDATE);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private void updateAppFeature(final String column, final boolean running, final Set<String> appIds) {
        var updateValues = new ContentValues();
        updateValues.put(column, false);
        if (!running) {
            db.update(DATABASE_TABLE_APPS, updateValues, null, null);
        }

        updateValues.put(column, true);
        for (var id : appIds) {
            int updated = db.update(DATABASE_TABLE_APPS, updateValues, DbConstants.APPS.ID + " = ?", new String[]{id});
            if (updated == 0) {
                var insertValues = new ContentValues();
                insertValues.put(DbConstants.APPS.ID, id);
                insertValues.put(column, true);
                db.insert(DATABASE_TABLE_APPS, null, insertValues);
            }
        }
    }

    public de.storchp.fdroidbuildstatus.model.App loadApp(String id) {
        if (id == null) {
            return null;
        }
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_APPS + " WHERE " + DbConstants.APPS.ID + " = ?", new String[]{id})) {
            if (cursor.moveToFirst()) {
                return createAppFromCursor(cursor);
            }
        }
        return null;
    }

    public Set<AppNotification> getNotificationsFor(BuildCycle buildCycle, long startTimestamp) {
        var notifiedApps = new HashSet<AppNotification>();
        try (Cursor cursor = db.query(DATABASE_TABLE_NOTIFICATIONS, new String[]{DbConstants.NOTIFICATIONS.ID, DbConstants.NOTIFICATIONS.VERSION_CODE},
                DbConstants.NOTIFICATIONS.BUILD_RUN_TYPE + " = ? AND " + DbConstants.NOTIFICATIONS.START + " = ?",
                new String[]{buildCycle.name(), String.valueOf(startTimestamp)}, null, null, null)) {
            while (cursor.moveToNext()) {
                notifiedApps.add(new AppNotification(
                        cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.NOTIFICATIONS.ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.NOTIFICATIONS.VERSION_CODE))));
            }
        }

        return notifiedApps;
    }

    public void saveNotifications(BuildCycle buildCycle, long startTimestamp, Set<AppNotification> notifiedApps) {
        db.beginTransaction();

        db.delete(DATABASE_TABLE_NOTIFICATIONS, DbConstants.NOTIFICATIONS.BUILD_RUN_TYPE + " = ?", new String[]{buildCycle.name()});
        for (AppNotification appNotification : notifiedApps) {
            var values = new ContentValues();
            values.put(DbConstants.NOTIFICATIONS.BUILD_RUN_TYPE, buildCycle.name());
            values.put(DbConstants.NOTIFICATIONS.START, startTimestamp);
            values.put(DbConstants.NOTIFICATIONS.ID, appNotification.getId());
            values.put(DbConstants.NOTIFICATIONS.VERSION_CODE, appNotification.getVersionCode());
            db.insert(DATABASE_TABLE_NOTIFICATIONS, null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<App> findApps(String search) {
        if (search == null) {
            return null;
        }
        var apps = new ArrayList<App>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_APPS + " WHERE " + DbConstants.APPS.ID + " = ? OR " + DbConstants.APPS.NAME + " LIKE ?", new String[]{search, search + "%"})) {
            while (cursor.moveToNext()) {
                apps.add(createAppFromCursor(cursor));
            }
        }
        return apps;
    }

}
