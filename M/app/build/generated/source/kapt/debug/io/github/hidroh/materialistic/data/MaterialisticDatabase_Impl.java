package io.github.hidroh.materialistic.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MaterialisticDatabase_Impl extends MaterialisticDatabase {
  private volatile MaterialisticDatabase.SavedStoriesDao _savedStoriesDao;

  private volatile MaterialisticDatabase.ReadStoriesDao _readStoriesDao;

  private volatile MaterialisticDatabase.ReadableDao _readableDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `saved` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemid` TEXT, `url` TEXT, `title` TEXT, `time` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `read` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemid` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Readable` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemid` TEXT, `content` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6d7efc4b6410ff6acc7727175ff34c99')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `saved`");
        _db.execSQL("DROP TABLE IF EXISTS `read`");
        _db.execSQL("DROP TABLE IF EXISTS `Readable`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
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
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSaved = new HashMap<String, TableInfo.Column>(5);
        _columnsSaved.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaved.put("itemid", new TableInfo.Column("itemid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaved.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaved.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaved.put("time", new TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSaved = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSaved = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSaved = new TableInfo("saved", _columnsSaved, _foreignKeysSaved, _indicesSaved);
        final TableInfo _existingSaved = TableInfo.read(_db, "saved");
        if (! _infoSaved.equals(_existingSaved)) {
          return new RoomOpenHelper.ValidationResult(false, "saved(io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStory).\n"
                  + " Expected:\n" + _infoSaved + "\n"
                  + " Found:\n" + _existingSaved);
        }
        final HashMap<String, TableInfo.Column> _columnsRead = new HashMap<String, TableInfo.Column>(2);
        _columnsRead.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRead.put("itemid", new TableInfo.Column("itemid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRead = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRead = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRead = new TableInfo("read", _columnsRead, _foreignKeysRead, _indicesRead);
        final TableInfo _existingRead = TableInfo.read(_db, "read");
        if (! _infoRead.equals(_existingRead)) {
          return new RoomOpenHelper.ValidationResult(false, "read(io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadStory).\n"
                  + " Expected:\n" + _infoRead + "\n"
                  + " Found:\n" + _existingRead);
        }
        final HashMap<String, TableInfo.Column> _columnsReadable = new HashMap<String, TableInfo.Column>(3);
        _columnsReadable.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadable.put("itemid", new TableInfo.Column("itemid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadable.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReadable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReadable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReadable = new TableInfo("Readable", _columnsReadable, _foreignKeysReadable, _indicesReadable);
        final TableInfo _existingReadable = TableInfo.read(_db, "Readable");
        if (! _infoReadable.equals(_existingReadable)) {
          return new RoomOpenHelper.ValidationResult(false, "Readable(io.github.hidroh.materialistic.data.MaterialisticDatabase.Readable).\n"
                  + " Expected:\n" + _infoReadable + "\n"
                  + " Found:\n" + _existingReadable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6d7efc4b6410ff6acc7727175ff34c99", "b4faee4f2c1e011a609939ca8b84cc44");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "saved","read","Readable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `saved`");
      _db.execSQL("DELETE FROM `read`");
      _db.execSQL("DELETE FROM `Readable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MaterialisticDatabase.SavedStoriesDao.class, MaterialisticDatabaseSavedStoriesDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MaterialisticDatabase.ReadStoriesDao.class, MaterialisticDatabaseReadStoriesDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MaterialisticDatabase.ReadableDao.class, MaterialisticDatabaseReadableDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public MaterialisticDatabase.SavedStoriesDao getSavedStoriesDao() {
    if (_savedStoriesDao != null) {
      return _savedStoriesDao;
    } else {
      synchronized(this) {
        if(_savedStoriesDao == null) {
          _savedStoriesDao = new MaterialisticDatabaseSavedStoriesDao_Impl(this);
        }
        return _savedStoriesDao;
      }
    }
  }

  @Override
  public MaterialisticDatabase.ReadStoriesDao getReadStoriesDao() {
    if (_readStoriesDao != null) {
      return _readStoriesDao;
    } else {
      synchronized(this) {
        if(_readStoriesDao == null) {
          _readStoriesDao = new MaterialisticDatabaseReadStoriesDao_Impl(this);
        }
        return _readStoriesDao;
      }
    }
  }

  @Override
  public MaterialisticDatabase.ReadableDao getReadableDao() {
    if (_readableDao != null) {
      return _readableDao;
    } else {
      synchronized(this) {
        if(_readableDao == null) {
          _readableDao = new MaterialisticDatabaseReadableDao_Impl(this);
        }
        return _readableDao;
      }
    }
  }
}
