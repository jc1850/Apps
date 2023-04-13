package org.adaway.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import org.adaway.db.converter.ListTypeConverter;
import org.adaway.db.entity.HostListItem;
import org.adaway.db.entity.ListType;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HostListItemDao_Impl implements HostListItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HostListItem> __insertionAdapterOfHostListItem;

  private final EntityDeletionOrUpdateAdapter<HostListItem> __deletionAdapterOfHostListItem;

  private final EntityDeletionOrUpdateAdapter<HostListItem> __updateAdapterOfHostListItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserFromHost;

  private final SharedSQLiteStatement __preparedStmtOfClearSourceHosts;

  public HostListItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHostListItem = new EntityInsertionAdapter<HostListItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `hosts_lists` (`id`,`host`,`type`,`enabled`,`redirection`,`source_id`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostListItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getHost() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHost());
        }
        final Integer _tmp = ListTypeConverter.typeToValue(value.getType());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final int _tmp_1 = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getRedirection() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRedirection());
        }
        stmt.bindLong(6, value.getSourceId());
      }
    };
    this.__deletionAdapterOfHostListItem = new EntityDeletionOrUpdateAdapter<HostListItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `hosts_lists` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostListItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfHostListItem = new EntityDeletionOrUpdateAdapter<HostListItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `hosts_lists` SET `id` = ?,`host` = ?,`type` = ?,`enabled` = ?,`redirection` = ?,`source_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostListItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getHost() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHost());
        }
        final Integer _tmp = ListTypeConverter.typeToValue(value.getType());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final int _tmp_1 = value.isEnabled() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getRedirection() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRedirection());
        }
        stmt.bindLong(6, value.getSourceId());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteUserFromHost = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM hosts_lists WHERE source_id = 1 AND host = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearSourceHosts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM hosts_lists WHERE source_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final HostListItem... item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHostListItem.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final List<HostListItem> items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHostListItem.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final HostListItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHostListItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final HostListItem item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHostListItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUserFromHost(final String host) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserFromHost.acquire();
    int _argIndex = 1;
    if (host == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, host);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUserFromHost.release(_stmt);
    }
  }

  @Override
  public void clearSourceHosts(final int sourceId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearSourceHosts.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, sourceId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearSourceHosts.release(_stmt);
    }
  }

  @Override
  public PagingSource<Integer, HostListItem> loadList(final int type, final boolean includeSources,
      final String query) {
    final String _sql = "SELECT * FROM hosts_lists WHERE type = ? AND host LIKE ? AND ((? == 0 AND source_id == 1) || (? == 1)) GROUP BY host ORDER BY host ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 3;
    final int _tmp = includeSources ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    _argIndex = 4;
    final int _tmp_1 = includeSources ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp_1);
    return new LimitOffsetPagingSource<HostListItem>(_statement, __db, "hosts_lists") {
      @Override
      protected List<HostListItem> convertRows(Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(cursor, "host");
        final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(cursor, "type");
        final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(cursor, "enabled");
        final int _cursorIndexOfRedirection = CursorUtil.getColumnIndexOrThrow(cursor, "redirection");
        final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(cursor, "source_id");
        final List<HostListItem> _result = new ArrayList<HostListItem>(cursor.getCount());
        while(cursor.moveToNext()) {
          final HostListItem _item;
          _item = new HostListItem();
          final int _tmpId;
          _tmpId = cursor.getInt(_cursorIndexOfId);
          _item.setId(_tmpId);
          final String _tmpHost;
          if (cursor.isNull(_cursorIndexOfHost)) {
            _tmpHost = null;
          } else {
            _tmpHost = cursor.getString(_cursorIndexOfHost);
          }
          _item.setHost(_tmpHost);
          final ListType _tmpType;
          final Integer _tmp_2;
          if (cursor.isNull(_cursorIndexOfType)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = cursor.getInt(_cursorIndexOfType);
          }
          _tmpType = ListTypeConverter.fromValue(_tmp_2);
          _item.setType(_tmpType);
          final boolean _tmpEnabled;
          final int _tmp_3;
          _tmp_3 = cursor.getInt(_cursorIndexOfEnabled);
          _tmpEnabled = _tmp_3 != 0;
          _item.setEnabled(_tmpEnabled);
          final String _tmpRedirection;
          if (cursor.isNull(_cursorIndexOfRedirection)) {
            _tmpRedirection = null;
          } else {
            _tmpRedirection = cursor.getString(_cursorIndexOfRedirection);
          }
          _item.setRedirection(_tmpRedirection);
          final int _tmpSourceId;
          _tmpSourceId = cursor.getInt(_cursorIndexOfSourceId);
          _item.setSourceId(_tmpSourceId);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public List<HostListItem> getAll() {
    final String _sql = "SELECT * FROM hosts_lists ORDER BY host ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfRedirection = CursorUtil.getColumnIndexOrThrow(_cursor, "redirection");
      final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "source_id");
      final List<HostListItem> _result = new ArrayList<HostListItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final HostListItem _item;
        _item = new HostListItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpHost;
        if (_cursor.isNull(_cursorIndexOfHost)) {
          _tmpHost = null;
        } else {
          _tmpHost = _cursor.getString(_cursorIndexOfHost);
        }
        _item.setHost(_tmpHost);
        final ListType _tmpType;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfType);
        }
        _tmpType = ListTypeConverter.fromValue(_tmp);
        _item.setType(_tmpType);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _item.setEnabled(_tmpEnabled);
        final String _tmpRedirection;
        if (_cursor.isNull(_cursorIndexOfRedirection)) {
          _tmpRedirection = null;
        } else {
          _tmpRedirection = _cursor.getString(_cursorIndexOfRedirection);
        }
        _item.setRedirection(_tmpRedirection);
        final int _tmpSourceId;
        _tmpSourceId = _cursor.getInt(_cursorIndexOfSourceId);
        _item.setSourceId(_tmpSourceId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<HostListItem> getUserList() {
    final String _sql = "SELECT * FROM hosts_lists WHERE source_id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfRedirection = CursorUtil.getColumnIndexOrThrow(_cursor, "redirection");
      final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "source_id");
      final List<HostListItem> _result = new ArrayList<HostListItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final HostListItem _item;
        _item = new HostListItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpHost;
        if (_cursor.isNull(_cursorIndexOfHost)) {
          _tmpHost = null;
        } else {
          _tmpHost = _cursor.getString(_cursorIndexOfHost);
        }
        _item.setHost(_tmpHost);
        final ListType _tmpType;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfType);
        }
        _tmpType = ListTypeConverter.fromValue(_tmp);
        _item.setType(_tmpType);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _item.setEnabled(_tmpEnabled);
        final String _tmpRedirection;
        if (_cursor.isNull(_cursorIndexOfRedirection)) {
          _tmpRedirection = null;
        } else {
          _tmpRedirection = _cursor.getString(_cursorIndexOfRedirection);
        }
        _item.setRedirection(_tmpRedirection);
        final int _tmpSourceId;
        _tmpSourceId = _cursor.getInt(_cursorIndexOfSourceId);
        _item.setSourceId(_tmpSourceId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Optional<Integer> getHostId(final String host) {
    final String _sql = "SELECT id FROM hosts_lists WHERE host = ? AND source_id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (host == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, host);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Integer _value;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _value = null;
        } else {
          _value = _cursor.getInt(0);
        }
      } else {
        _value = null;
      }
      final Optional<Integer> _result = Optional.ofNullable(_value);
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Integer> getBlockedHostCount() {
    final String _sql = "SELECT COUNT(DISTINCT host) FROM hosts_lists WHERE type = 0 AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_lists"}, false, new Callable<Integer>() {
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
  public LiveData<Integer> getAllowedHostCount() {
    final String _sql = "SELECT COUNT(DISTINCT host) FROM hosts_lists WHERE type = 1 AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_lists"}, false, new Callable<Integer>() {
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
  public LiveData<Integer> getRedirectHostCount() {
    final String _sql = "SELECT COUNT(DISTINCT host) FROM hosts_lists WHERE type = 2 AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"hosts_lists"}, false, new Callable<Integer>() {
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
