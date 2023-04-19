package de.storchp.fdroidbuildstatus.adapter.db;

import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_NAME;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_APPS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_BUILDS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_BUILD_RUNS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_DISABLED;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_NEEDS_UPDATE;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_NOTIFICATIONS;
import static de.storchp.fdroidbuildstatus.adapter.db.DbConstants.DATABASE_TABLE_VERSIONS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DbOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DbOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 14;

    private static final String CREATE_STATEMENT_APPS = "CREATE TABLE " + DATABASE_TABLE_APPS + " ("
            + DbConstants.APPS.ID + " TEXT PRIMARY KEY,"
            + DbConstants.APPS.NAME + " TEXT,"
            + DbConstants.APPS.FAVOURITE + " INTEGER,"
            + DbConstants.APPS.DISABLED + " INTEGER,"
            + DbConstants.APPS.NEEDS_UPDATE + " INTEGER,"
            + DbConstants.APPS.SOURCE_CODE + " TEXT,"
            + DbConstants.APPS.ARCHIVED + " INTEGER,"
            + DbConstants.APPS.NO_PACKAGES + " INTEGER,"
            + DbConstants.APPS.NO_UPDATE_CHECK + " INTEGER)";

    private static final String CREATE_STATEMENT_BUILD_RUNS = "CREATE TABLE " + DATABASE_TABLE_BUILD_RUNS + " ("
            + DbConstants.BUILD_RUNS.BUILD_RUN_TYPE + " TEXT PRIMARY KEY,"
            + DbConstants.BUILD_RUNS.START + " INTEGER,"
            + "\"" + DbConstants.BUILD_RUNS.END + "\"" + " INTEGER,"
            + DbConstants.BUILD_RUNS.LAST_MODIFIED + " INTEGER,"
            + DbConstants.BUILD_RUNS.LAST_UPDATED + " INTEGER,"
            + DbConstants.BUILD_RUNS.MAX_BUILD_TIME_REACHED + " INTEGER,"
            + DbConstants.BUILD_RUNS.SUBCOMMAND + " TEXT,"
            + DbConstants.BUILD_RUNS.DATA_COMMIT_ID + " TEXT)";

    private static final String CREATE_STATEMENT_BUILDS = "CREATE TABLE " + DATABASE_TABLE_BUILDS + " ("
            + DbConstants.BUILDS.BUILD_RUN_TYPE + " TEXT,"
            + DbConstants.BUILDS.ID + " TEXT,"
            + DbConstants.BUILDS.VERSION_CODE + " TEXT,"
            + DbConstants.BUILDS.STATUS + " TEXT,"
            + DbConstants.BUILDS.LOG + " TEXT,"
            + "PRIMARY KEY (" + DbConstants.BUILDS.BUILD_RUN_TYPE + ", " + DbConstants.BUILDS.ID + ", " + DbConstants.BUILDS.VERSION_CODE + "))";

    private static final String CREATE_STATEMENT_VERSIONS = "CREATE TABLE " + DATABASE_TABLE_VERSIONS + " ("
            + DbConstants.VERSIONS.ID + " TEXT,"
            + DbConstants.VERSIONS.VERSION_CODE + " TEXT,"
            + DbConstants.VERSIONS.VERSION_NAME + " TEXT,"
            + "PRIMARY KEY (" + DbConstants.VERSIONS.ID + ", " + DbConstants.VERSIONS.VERSION_CODE + "))";

    private static final String CREATE_STATEMENT_DISABLED = "CREATE TABLE " + DATABASE_TABLE_DISABLED + " ("
            + DbConstants.APPS.ID + " TEXT PRIMARY KEY)";

    private static final String CREATE_STATEMENT_NEEDS_UPDATE = "CREATE TABLE " + DATABASE_TABLE_NEEDS_UPDATE + " ("
            + DbConstants.APPS.ID + " TEXT PRIMARY KEY)";

    private static final String CREATE_STATEMENT_NOTIFICATIONS = "CREATE TABLE " + DATABASE_TABLE_NOTIFICATIONS + " ("
            + DbConstants.NOTIFICATIONS.BUILD_RUN_TYPE + " TEXT,"
            + DbConstants.NOTIFICATIONS.START + " INTEGER,"
            + DbConstants.NOTIFICATIONS.ID + " TEXT,"
            + DbConstants.NOTIFICATIONS.VERSION_CODE + " TEXT,"
            + "PRIMARY KEY (" + DbConstants.NOTIFICATIONS.BUILD_RUN_TYPE + ", " + DbConstants.NOTIFICATIONS.START + ", " + DbConstants.NOTIFICATIONS.ID + ", " + DbConstants.NOTIFICATIONS.VERSION_CODE + "))";

    DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database");
        db.execSQL(CREATE_STATEMENT_APPS);
        db.execSQL(CREATE_STATEMENT_BUILD_RUNS);
        db.execSQL(CREATE_STATEMENT_BUILDS);
        db.execSQL(CREATE_STATEMENT_VERSIONS);
        db.execSQL(CREATE_STATEMENT_NOTIFICATIONS);
        Log.i(TAG, "Database structure created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrade database from version" + oldVersion + " to " + newVersion);
        db.beginTransaction();

        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.NAME + " TEXT");
        }
        if (oldVersion < 3) {
            db.execSQL(CREATE_STATEMENT_BUILD_RUNS);
            db.execSQL(CREATE_STATEMENT_BUILDS);
        }
        if (oldVersion == 3) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_BUILD_RUNS + " ADD COLUMN " + DbConstants.BUILD_RUNS.MAX_BUILD_TIME_REACHED + " INTEGER");
        }
        if (oldVersion >= 3 && oldVersion < 5) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_BUILD_RUNS + " ADD COLUMN " + DbConstants.BUILD_RUNS.SUBCOMMAND + " TEXT");
        }
        if (oldVersion >= 3 && oldVersion < 6) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_BUILD_RUNS + " ADD COLUMN " + DbConstants.BUILD_RUNS.DATA_COMMIT_ID + " TEXT");
        }
        if (oldVersion >= 3 && oldVersion < 7) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_BUILDS + " ADD COLUMN sourceCode TEXT");
        }
        if (oldVersion < 9) {
            if (!isTableExists(db, DATABASE_TABLE_DISABLED)) {
                db.execSQL(CREATE_STATEMENT_DISABLED);
            }
            if (!isTableExists(db, DATABASE_TABLE_NEEDS_UPDATE)) {
                db.execSQL(CREATE_STATEMENT_NEEDS_UPDATE);
            }
        }
        if (oldVersion < 10) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.DISABLED + " INTEGER");
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.NEEDS_UPDATE + " INTEGER");
            db.execSQL("UPDATE " + DATABASE_TABLE_APPS + " SET " + DbConstants.APPS.DISABLED + " = 1 WHERE " + DbConstants.APPS.ID + " IN (SELECT " + DbConstants.DISABLED.ID + " FROM " + DATABASE_TABLE_DISABLED + ")");
            db.execSQL("UPDATE " + DATABASE_TABLE_APPS + " SET " + DbConstants.APPS.NEEDS_UPDATE + " = 1 WHERE " + DbConstants.APPS.ID + " IN (SELECT " + DbConstants.NEEDS_UPDATE.ID + " FROM " + DATABASE_TABLE_NEEDS_UPDATE + ")");
            if (isTableExists(db, DATABASE_TABLE_DISABLED)) {
                db.execSQL("DROP TABLE " + DATABASE_TABLE_DISABLED);
            }
            if (isTableExists(db, DATABASE_TABLE_NEEDS_UPDATE)) {
                db.execSQL("DROP TABLE " + DATABASE_TABLE_NEEDS_UPDATE);
            }
        }
        if (oldVersion < 11) {
            db.execSQL(CREATE_STATEMENT_NOTIFICATIONS);
        }
        if (oldVersion < 12) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.SOURCE_CODE + " TEXT");
        }
        if (oldVersion < 13) {
            db.execSQL(CREATE_STATEMENT_VERSIONS);
            db.execSQL("INSERT INTO " + DATABASE_TABLE_VERSIONS + " (" + DbConstants.VERSIONS.ID + ", " + DbConstants.VERSIONS.VERSION_CODE + ", " + DbConstants.VERSIONS.VERSION_NAME + ")" +
                    " SELECT " + DbConstants.BUILDS.ID + ", " + DbConstants.BUILDS.VERSION_CODE + ", MAX(" + DbConstants.BUILDS.VERSION + ")" +
                    " FROM " + DATABASE_TABLE_BUILDS +
                    " WHERE " + DbConstants.BUILDS.VERSION + " IS NOT NULL" +
                    " GROUP BY " + DbConstants.BUILDS.ID + ", " + DbConstants.BUILDS.VERSION_CODE);
        }
        if (oldVersion < 14) {
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.ARCHIVED + " INTEGER");
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.NO_PACKAGES + " INTEGER");
            db.execSQL("ALTER TABLE " + DATABASE_TABLE_APPS + " ADD COLUMN " + DbConstants.APPS.NO_UPDATE_CHECK + " INTEGER");
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        try (Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?", new String[]{tableName})) {
            if (cursor != null) {
                return cursor.getCount() > 0;
            }
            return false;
        }
    }

}
