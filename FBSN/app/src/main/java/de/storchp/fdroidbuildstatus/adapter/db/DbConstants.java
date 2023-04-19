package de.storchp.fdroidbuildstatus.adapter.db;

public interface DbConstants {

    String DATABASE_NAME = "fdroidbuildstatus.db";

    String DATABASE_TABLE_APPS = "apps";
    String DATABASE_TABLE_BUILD_RUNS = "build_runs";
    String DATABASE_TABLE_BUILDS = "builds";
    String DATABASE_TABLE_VERSIONS = "versions";
    String DATABASE_TABLE_DISABLED = "disabled";
    String DATABASE_TABLE_NEEDS_UPDATE = "needs_update";
    String DATABASE_TABLE_NOTIFICATIONS = "notifications";

    String COUNTER = "COUNTER";

    interface APPS {
        String ID = "id";
        String NAME = "name";
        String FAVOURITE = "favourite";
        String DISABLED = "disabled";
        String NEEDS_UPDATE = "needs_update";
        String SOURCE_CODE = "sourceCode";
        String ARCHIVED = "archived";
        String NO_PACKAGES = "noPackages";
        String NO_UPDATE_CHECK = "noUpdateCheck";
    }

    interface BUILDS {
        String BUILD_RUN_TYPE = "buildRunType";
        String ID = "id";
        String VERSION = "version";
        String VERSION_CODE = "versionCode";
        String STATUS = "status";
        String LOG = "error"; // was previous only error log
    }

    interface VERSIONS {
        String ID = "id";
        String VERSION_CODE = "versionCode";
        String VERSION_NAME = "versionName";
    }

    interface BUILD_RUNS {
        String BUILD_RUN_TYPE = "buildRunType";
        String START = "start";
        String END = "end";
        String LAST_MODIFIED = "lastModified";
        String LAST_UPDATED = "lastUpdated";
        String MAX_BUILD_TIME_REACHED = "maxBuildTimeReached";
        String SUBCOMMAND = "subcommand";
        String DATA_COMMIT_ID = "commitId";
    }

    interface DISABLED {
        String ID = "id";
    }

    interface NEEDS_UPDATE {
        String ID = "id";
    }

    interface NOTIFICATIONS {
        String BUILD_RUN_TYPE = "buildRunType";
        String START = "start";
        String ID = "id";
        String VERSION_CODE = "versionCode";
    }

}
