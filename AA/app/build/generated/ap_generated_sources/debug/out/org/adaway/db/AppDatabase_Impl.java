package org.adaway.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.adaway.db.dao.HostEntryDao;
import org.adaway.db.dao.HostEntryDao_Impl;
import org.adaway.db.dao.HostListItemDao;
import org.adaway.db.dao.HostListItemDao_Impl;
import org.adaway.db.dao.HostsSourceDao;
import org.adaway.db.dao.HostsSourceDao_Impl;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile HostsSourceDao _hostsSourceDao;

  private volatile HostListItemDao _hostListItemDao;

  private volatile HostEntryDao _hostEntryDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `hosts_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `label` TEXT NOT NULL, `url` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `allowEnabled` INTEGER NOT NULL, `redirectEnabled` INTEGER NOT NULL, `last_modified_local` INTEGER, `last_modified_online` INTEGER, `entityTag` TEXT, `size` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_hosts_sources_url` ON `hosts_sources` (`url`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `hosts_lists` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `host` TEXT NOT NULL, `type` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, `redirection` TEXT, `source_id` INTEGER NOT NULL, FOREIGN KEY(`source_id`) REFERENCES `hosts_sources`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_hosts_lists_host` ON `hosts_lists` (`host`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_hosts_lists_source_id` ON `hosts_lists` (`source_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `host_entries` (`host` TEXT NOT NULL, `type` INTEGER NOT NULL, `redirection` TEXT, PRIMARY KEY(`host`))");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_host_entries_host` ON `host_entries` (`host`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'dccd6211bcef97caed75ea42d7df1b32')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `hosts_sources`");
        _db.execSQL("DROP TABLE IF EXISTS `hosts_lists`");
        _db.execSQL("DROP TABLE IF EXISTS `host_entries`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsHostsSources = new HashMap<String, TableInfo.Column>(10);
        _columnsHostsSources.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("label", new TableInfo.Column("label", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("allowEnabled", new TableInfo.Column("allowEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("redirectEnabled", new TableInfo.Column("redirectEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("last_modified_local", new TableInfo.Column("last_modified_local", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("last_modified_online", new TableInfo.Column("last_modified_online", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("entityTag", new TableInfo.Column("entityTag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsSources.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHostsSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHostsSources = new HashSet<TableInfo.Index>(1);
        _indicesHostsSources.add(new TableInfo.Index("index_hosts_sources_url", true, Arrays.asList("url"), Arrays.asList("ASC")));
        final TableInfo _infoHostsSources = new TableInfo("hosts_sources", _columnsHostsSources, _foreignKeysHostsSources, _indicesHostsSources);
        final TableInfo _existingHostsSources = TableInfo.read(_db, "hosts_sources");
        if (! _infoHostsSources.equals(_existingHostsSources)) {
          return new RoomOpenHelper.ValidationResult(false, "hosts_sources(org.adaway.db.entity.HostsSource).\n"
                  + " Expected:\n" + _infoHostsSources + "\n"
                  + " Found:\n" + _existingHostsSources);
        }
        final HashMap<String, TableInfo.Column> _columnsHostsLists = new HashMap<String, TableInfo.Column>(6);
        _columnsHostsLists.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsLists.put("host", new TableInfo.Column("host", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsLists.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsLists.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsLists.put("redirection", new TableInfo.Column("redirection", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostsLists.put("source_id", new TableInfo.Column("source_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHostsLists = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysHostsLists.add(new TableInfo.ForeignKey("hosts_sources", "CASCADE", "CASCADE",Arrays.asList("source_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesHostsLists = new HashSet<TableInfo.Index>(2);
        _indicesHostsLists.add(new TableInfo.Index("index_hosts_lists_host", false, Arrays.asList("host"), Arrays.asList("ASC")));
        _indicesHostsLists.add(new TableInfo.Index("index_hosts_lists_source_id", false, Arrays.asList("source_id"), Arrays.asList("ASC")));
        final TableInfo _infoHostsLists = new TableInfo("hosts_lists", _columnsHostsLists, _foreignKeysHostsLists, _indicesHostsLists);
        final TableInfo _existingHostsLists = TableInfo.read(_db, "hosts_lists");
        if (! _infoHostsLists.equals(_existingHostsLists)) {
          return new RoomOpenHelper.ValidationResult(false, "hosts_lists(org.adaway.db.entity.HostListItem).\n"
                  + " Expected:\n" + _infoHostsLists + "\n"
                  + " Found:\n" + _existingHostsLists);
        }
        final HashMap<String, TableInfo.Column> _columnsHostEntries = new HashMap<String, TableInfo.Column>(3);
        _columnsHostEntries.put("host", new TableInfo.Column("host", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostEntries.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHostEntries.put("redirection", new TableInfo.Column("redirection", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHostEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHostEntries = new HashSet<TableInfo.Index>(1);
        _indicesHostEntries.add(new TableInfo.Index("index_host_entries_host", true, Arrays.asList("host"), Arrays.asList("ASC")));
        final TableInfo _infoHostEntries = new TableInfo("host_entries", _columnsHostEntries, _foreignKeysHostEntries, _indicesHostEntries);
        final TableInfo _existingHostEntries = TableInfo.read(_db, "host_entries");
        if (! _infoHostEntries.equals(_existingHostEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "host_entries(org.adaway.db.entity.HostEntry).\n"
                  + " Expected:\n" + _infoHostEntries + "\n"
                  + " Found:\n" + _existingHostEntries);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "dccd6211bcef97caed75ea42d7df1b32", "de1ecedb30865e6c712cdc68be28cf39");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "hosts_sources","hosts_lists","host_entries");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `hosts_sources`");
      _db.execSQL("DELETE FROM `hosts_lists`");
      _db.execSQL("DELETE FROM `host_entries`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HostsSourceDao.class, HostsSourceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HostListItemDao.class, HostListItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HostEntryDao.class, HostEntryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public HostsSourceDao hostsSourceDao() {
    if (_hostsSourceDao != null) {
      return _hostsSourceDao;
    } else {
      synchronized(this) {
        if(_hostsSourceDao == null) {
          _hostsSourceDao = new HostsSourceDao_Impl(this);
        }
        return _hostsSourceDao;
      }
    }
  }

  @Override
  public HostListItemDao hostsListItemDao() {
    if (_hostListItemDao != null) {
      return _hostListItemDao;
    } else {
      synchronized(this) {
        if(_hostListItemDao == null) {
          _hostListItemDao = new HostListItemDao_Impl(this);
        }
        return _hostListItemDao;
      }
    }
  }

  @Override
  public HostEntryDao hostEntryDao() {
    if (_hostEntryDao != null) {
      return _hostEntryDao;
    } else {
      synchronized(this) {
        if(_hostEntryDao == null) {
          _hostEntryDao = new HostEntryDao_Impl(this);
        }
        return _hostEntryDao;
      }
    }
  }
}
