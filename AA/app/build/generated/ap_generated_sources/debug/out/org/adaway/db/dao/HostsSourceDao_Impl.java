package org.adaway.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import org.adaway.db.converter.ZonedDateTimeConverter;
import org.adaway.db.entity.HostsSource;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HostsSourceDao_Impl implements HostsSourceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HostsSource> __insertionAdapterOfHostsSource;

  private final EntityDeletionOrUpdateAdapter<HostsSource> __deletionAdapterOfHostsSource;

  private final EntityDeletionOrUpdateAdapter<HostsSource> __updateAdapterOfHostsSource;

  private final SharedSQLiteStatement __preparedStmtOfSetSourceEnabled;

  private final SharedSQLiteStatement __preparedStmtOfSetSourceItemsEnabled;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOnlineModificationDate;

  private final SharedSQLiteStatement __preparedStmtOfUpdateModificationDates;

  private final SharedSQLiteStatement __preparedStmtOfUpdateEntityTag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSize;

  private final SharedSQLiteStatement __preparedStmtOfClearProperties;

  public HostsSourceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHostsSource = new EntityInsertionAdapter<HostsSource>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `hosts_sources` (`id`,`label`,`url`,`enabled`,`allowEnabled`,`redirectEnabled`,`last_modified_local`,`last_modified_online`,`entityTag`,`size`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostsSource value) {
        stmt.bindLong(1, value.getId());
        if (value.getLabel() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLabel());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        final int _tmp_1 = value.isAllowEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp_1);
        final int _tmp_2 = value.isRedirectEnabled() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
        final Long _tmp_3 = ZonedDateTimeConverter.toTimestamp(value.getLocalModificationDate());
        if (_tmp_3 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, _tmp_3);
        }
        final Long _tmp_4 = ZonedDateTimeConverter.toTimestamp(value.getOnlineModificationDate());
        if (_tmp_4 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp_4);
        }
        if (value.getEntityTag() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getEntityTag());
        }
        stmt.bindLong(10, value.getSize());
      }
    };
    this.__deletionAdapterOfHostsSource = new EntityDeletionOrUpdateAdapter<HostsSource>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `hosts_sources` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostsSource value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfHostsSource = new EntityDeletionOrUpdateAdapter<HostsSource>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `hosts_sources` SET `id` = ?,`label` = ?,`url` = ?,`enabled` = ?,`allowEnabled` = ?,`redirectEnabled` = ?,`last_modified_local` = ?,`last_modified_online` = ?,`entityTag` = ?,`size` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostsSource value) {
        stmt.bindLong(1, value.getId());
        if (value.getLabel() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLabel());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        final int _tmp_1 = value.isAllowEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp_1);
        final int _tmp_2 = value.isRedirectEnabled() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
        final Long _tmp_3 = ZonedDateTimeConverter.toTimestamp(value.getLocalModificationDate());
        if (_tmp_3 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, _tmp_3);
        }
        final Long _tmp_4 = ZonedDateTimeConverter.toTimestamp(value.getOnlineModificationDate());
        if (_tmp_4 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp_4);
        }
        if (value.getEntityTag() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getEntityTag());
        }
        stmt.bindLong(10, value.getSize());
        stmt.bindLong(11, value.getId());
      }
    };
    this.__preparedStmtOfSetSourceEnabled = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET enabled = ? WHERE id =?";
        return _query;
      }
    };
    this.__preparedStmtOfSetSourceItemsEnabled = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_lists SET enabled = ? WHERE source_id =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateOnlineModificationDate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET last_modified_online = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateModificationDates = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET last_modified_local = ?, last_modified_online = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateEntityTag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET entityTag = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSize = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET size = (SELECT count(id) FROM hosts_lists WHERE source_id = ?) WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearProperties = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE hosts_sources SET last_modified_local = NULL, last_modified_online = NULL, entityTag = NULL, size = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final HostsSource source) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHostsSource.insert(source);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final HostsSource source) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHostsSource.handle(source);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final HostsSource source) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHostsSource.handle(source);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setSourceEnabled(final int id, final boolean enabled) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetSourceEnabled.acquire();
    int _argIndex = 1;
    final int _tmp = enabled ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetSourceEnabled.release(_stmt);
    }
  }

  @Override
  public void setSourceItemsEnabled(final int id, final boolean enabled) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetSourceItemsEnabled.acquire();
    int _argIndex = 1;
    final int _tmp = enabled ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetSourceItemsEnabled.release(_stmt);
    }
  }

  @Override
  public void updateOnlineModificationDate(final int id, final ZonedDateTime dateTime) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOnlineModificationDate.acquire();
    int _argIndex = 1;
    final Long _tmp = ZonedDateTimeConverter.toTimestamp(dateTime);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateOnlineModificationDate.release(_stmt);
    }
  }

  @Override
  public void updateModificationDates(final int id, final ZonedDateTime localModificationDate,
      final ZonedDateTime onlineModificationDate) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateModificationDates.acquire();
    int _argIndex = 1;
    final Long _tmp = ZonedDateTimeConverter.toTimestamp(localModificationDate);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = ZonedDateTimeConverter.toTimestamp(onlineModificationDate);
    if (_tmp_1 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindLong(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateModificationDates.release(_stmt);
    }
  }

  @Override
  public void updateEntityTag(final int id, final String entityTag) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateEntityTag.acquire();
    int _argIndex = 1;
    if (entityTag == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, entityTag);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateEntityTag.release(_stmt);
    }
  }

  @Override
  public void updateSize(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSize.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSize.release(_stmt);
    }
  }

  @Override
  public void clearProperties(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearProperties.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearProperties.release(_stmt);
    }
  }

  @Override
  public List<HostsSource> getEnabled() {
    final String _sql = "SELECT * FROM hosts_sources WHERE enabled = 1 AND id != 1 ORDER BY url ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfAllowEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "allowEnabled");
      final int _cursorIndexOfRedirectEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "redirectEnabled");
      final int _cursorIndexOfLocalModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_local");
      final int _cursorIndexOfOnlineModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_online");
      final int _cursorIndexOfEntityTag = CursorUtil.getColumnIndexOrThrow(_cursor, "entityTag");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final List<HostsSource> _result = new ArrayList<HostsSource>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final HostsSource _item;
        _item = new HostsSource();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpLabel;
        if (_cursor.isNull(_cursorIndexOfLabel)) {
          _tmpLabel = null;
        } else {
          _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        }
        _item.setLabel(_tmpLabel);
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        _item.setUrl(_tmpUrl);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _item.setEnabled(_tmpEnabled);
        final boolean _tmpAllowEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAllowEnabled);
        _tmpAllowEnabled = _tmp_1 != 0;
        _item.setAllowEnabled(_tmpAllowEnabled);
        final boolean _tmpRedirectEnabled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfRedirectEnabled);
        _tmpRedirectEnabled = _tmp_2 != 0;
        _item.setRedirectEnabled(_tmpRedirectEnabled);
        final ZonedDateTime _tmpLocalModificationDate;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfLocalModificationDate)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfLocalModificationDate);
        }
        _tmpLocalModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_3);
        _item.setLocalModificationDate(_tmpLocalModificationDate);
        final ZonedDateTime _tmpOnlineModificationDate;
        final Long _tmp_4;
        if (_cursor.isNull(_cursorIndexOfOnlineModificationDate)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getLong(_cursorIndexOfOnlineModificationDate);
        }
        _tmpOnlineModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_4);
        _item.setOnlineModificationDate(_tmpOnlineModificationDate);
        final String _tmpEntityTag;
        if (_cursor.isNull(_cursorIndexOfEntityTag)) {
          _tmpEntityTag = null;
        } else {
          _tmpEntityTag = _cursor.getString(_cursorIndexOfEntityTag);
        }
        _item.setEntityTag(_tmpEntityTag);
        final int _tmpSize;
        _tmpSize = _cursor.getInt(_cursorIndexOfSize);
        _item.setSize(_tmpSize);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Optional<HostsSource> getById(final int id) {
    final String _sql = "SELECT * FROM hosts_sources WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfAllowEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "allowEnabled");
      final int _cursorIndexOfRedirectEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "redirectEnabled");
      final int _cursorIndexOfLocalModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_local");
      final int _cursorIndexOfOnlineModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_online");
      final int _cursorIndexOfEntityTag = CursorUtil.getColumnIndexOrThrow(_cursor, "entityTag");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final HostsSource _value;
      if(_cursor.moveToFirst()) {
        _value = new HostsSource();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _value.setId(_tmpId);
        final String _tmpLabel;
        if (_cursor.isNull(_cursorIndexOfLabel)) {
          _tmpLabel = null;
        } else {
          _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        }
        _value.setLabel(_tmpLabel);
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        _value.setUrl(_tmpUrl);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _value.setEnabled(_tmpEnabled);
        final boolean _tmpAllowEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAllowEnabled);
        _tmpAllowEnabled = _tmp_1 != 0;
        _value.setAllowEnabled(_tmpAllowEnabled);
        final boolean _tmpRedirectEnabled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfRedirectEnabled);
        _tmpRedirectEnabled = _tmp_2 != 0;
        _value.setRedirectEnabled(_tmpRedirectEnabled);
        final ZonedDateTime _tmpLocalModificationDate;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfLocalModificationDate)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfLocalModificationDate);
        }
        _tmpLocalModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_3);
        _value.setLocalModificationDate(_tmpLocalModificationDate);
        final ZonedDateTime _tmpOnlineModificationDate;
        final Long _tmp_4;
        if (_cursor.isNull(_cursorIndexOfOnlineModificationDate)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getLong(_cursorIndexOfOnlineModificationDate);
        }
        _tmpOnlineModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_4);
        _value.setOnlineModificationDate(_tmpOnlineModificationDate);
        final String _tmpEntityTag;
        if (_cursor.isNull(_cursorIndexOfEntityTag)) {
          _tmpEntityTag = null;
        } else {
          _tmpEntityTag = _cursor.getString(_cursorIndexOfEntityTag);
        }
        _value.setEntityTag(_tmpEntityTag);
        final int _tmpSize;
        _tmpSize = _cursor.getInt(_cursorIndexOfSize);
        _value.setSize(_tmpSize);
      } else {
        _value = null;
      }
      final Optional<HostsSource> _result = Optional.ofNullable(_value);
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<HostsSource> getAll() {
    final String _sql = "SELECT * FROM hosts_sources WHERE id != 1 ORDER BY label ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfAllowEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "allowEnabled");
      final int _cursorIndexOfRedirectEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "redirectEnabled");
      final int _cursorIndexOfLocalModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_local");
      final int _cursorIndexOfOnlineModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_online");
      final int _cursorIndexOfEntityTag = CursorUtil.getColumnIndexOrThrow(_cursor, "entityTag");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final List<HostsSource> _result = new ArrayList<HostsSource>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final HostsSource _item;
        _item = new HostsSource();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpLabel;
        if (_cursor.isNull(_cursorIndexOfLabel)) {
          _tmpLabel = null;
        } else {
          _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        }
        _item.setLabel(_tmpLabel);
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        _item.setUrl(_tmpUrl);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _item.setEnabled(_tmpEnabled);
        final boolean _tmpAllowEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAllowEnabled);
        _tmpAllowEnabled = _tmp_1 != 0;
        _item.setAllowEnabled(_tmpAllowEnabled);
        final boolean _tmpRedirectEnabled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfRedirectEnabled);
        _tmpRedirectEnabled = _tmp_2 != 0;
        _item.setRedirectEnabled(_tmpRedirectEnabled);
        final ZonedDateTime _tmpLocalModificationDate;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfLocalModificationDate)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfLocalModificationDate);
        }
        _tmpLocalModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_3);
        _item.setLocalModificationDate(_tmpLocalModificationDate);
        final ZonedDateTime _tmpOnlineModificationDate;
        final Long _tmp_4;
        if (_cursor.isNull(_cursorIndexOfOnlineModificationDate)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getLong(_cursorIndexOfOnlineModificationDate);
        }
        _tmpOnlineModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_4);
        _item.setOnlineModificationDate(_tmpOnlineModificationDate);
        final String _tmpEntityTag;
        if (_cursor.isNull(_cursorIndexOfEntityTag)) {
          _tmpEntityTag = null;
        } else {
          _tmpEntityTag = _cursor.getString(_cursorIndexOfEntityTag);
        }
        _item.setEntityTag(_tmpEntityTag);
        final int _tmpSize;
        _tmpSize = _cursor.getInt(_cursorIndexOfSize);
        _item.setSize(_tmpSize);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<HostsSource>> loadAll() {
    final String _sql = "SELECT * FROM hosts_sources WHERE id != 1 ORDER BY label ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_sources"}, false, new Callable<List<HostsSource>>() {
      @Override
      public List<HostsSource> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfAllowEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "allowEnabled");
          final int _cursorIndexOfRedirectEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "redirectEnabled");
          final int _cursorIndexOfLocalModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_local");
          final int _cursorIndexOfOnlineModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_modified_online");
          final int _cursorIndexOfEntityTag = CursorUtil.getColumnIndexOrThrow(_cursor, "entityTag");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final List<HostsSource> _result = new ArrayList<HostsSource>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final HostsSource _item;
            _item = new HostsSource();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            _item.setLabel(_tmpLabel);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            _item.setUrl(_tmpUrl);
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _item.setEnabled(_tmpEnabled);
            final boolean _tmpAllowEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAllowEnabled);
            _tmpAllowEnabled = _tmp_1 != 0;
            _item.setAllowEnabled(_tmpAllowEnabled);
            final boolean _tmpRedirectEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfRedirectEnabled);
            _tmpRedirectEnabled = _tmp_2 != 0;
            _item.setRedirectEnabled(_tmpRedirectEnabled);
            final ZonedDateTime _tmpLocalModificationDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLocalModificationDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfLocalModificationDate);
            }
            _tmpLocalModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_3);
            _item.setLocalModificationDate(_tmpLocalModificationDate);
            final ZonedDateTime _tmpOnlineModificationDate;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfOnlineModificationDate)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfOnlineModificationDate);
            }
            _tmpOnlineModificationDate = ZonedDateTimeConverter.fromTimestamp(_tmp_4);
            _item.setOnlineModificationDate(_tmpOnlineModificationDate);
            final String _tmpEntityTag;
            if (_cursor.isNull(_cursorIndexOfEntityTag)) {
              _tmpEntityTag = null;
            } else {
              _tmpEntityTag = _cursor.getString(_cursorIndexOfEntityTag);
            }
            _item.setEntityTag(_tmpEntityTag);
            final int _tmpSize;
            _tmpSize = _cursor.getInt(_cursorIndexOfSize);
            _item.setSize(_tmpSize);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> countOutdated() {
    final String _sql = "SELECT count(id) FROM hosts_sources WHERE enabled = 1 AND last_modified_online > last_modified_local";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_sources"}, false, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> countUpToDate() {
    final String _sql = "SELECT count(id) FROM hosts_sources WHERE enabled = 1 AND last_modified_online <= last_modified_local";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_sources"}, false, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
