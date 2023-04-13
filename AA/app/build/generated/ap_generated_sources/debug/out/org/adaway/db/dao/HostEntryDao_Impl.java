package org.adaway.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.adaway.db.converter.ListTypeConverter;
import org.adaway.db.entity.HostEntry;
import org.adaway.db.entity.HostListItem;
import org.adaway.db.entity.ListType;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HostEntryDao_Impl implements HostEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HostEntry> __insertionAdapterOfHostEntry;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  private final SharedSQLiteStatement __preparedStmtOfImportBlocked;

  private final SharedSQLiteStatement __preparedStmtOfAllowHost;

  public HostEntryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHostEntry = new EntityInsertionAdapter<HostEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `host_entries` (`host`,`type`,`redirection`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HostEntry value) {
        if (value.getHost() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getHost());
        }
        final Integer _tmp = ListTypeConverter.typeToValue(value.getType());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        if (value.getRedirection() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRedirection());
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `host_entries`";
        return _query;
      }
    };
    this.__preparedStmtOfImportBlocked = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "INSERT INTO `host_entries` SELECT DISTINCT `host`, `type`, `redirection` FROM `hosts_lists` WHERE `type` = 0 AND `enabled` = 1";
        return _query;
      }
    };
    this.__preparedStmtOfAllowHost = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `host_entries` WHERE `host` LIKE ?";
        return _query;
      }
    };
  }

  @Override
  public void redirectHost(final HostEntry redirection) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHostEntry.insert(redirection);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clear() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClear.release(_stmt);
    }
  }

  @Override
  public void importBlocked() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfImportBlocked.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeInsert();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfImportBlocked.release(_stmt);
    }
  }

  @Override
  public void allowHost(final String hostPattern) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfAllowHost.acquire();
    int _argIndex = 1;
    if (hostPattern == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, hostPattern);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfAllowHost.release(_stmt);
    }
  }

  @Override
  public List<String> getEnabledAllowedHosts() {
    final String _sql = "SELECT host FROM hosts_lists WHERE type = 1 AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<HostListItem> getEnabledRedirectedHosts() {
    final String _sql = "SELECT * FROM hosts_lists WHERE type = 2 AND enabled = 1 ORDER BY host ASC, source_id DESC";
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
  public List<HostEntry> getAll() {
    final String _sql = "SELECT * FROM `host_entries` ORDER BY `host`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfRedirection = CursorUtil.getColumnIndexOrThrow(_cursor, "redirection");
      final List<HostEntry> _result = new ArrayList<HostEntry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final HostEntry _item;
        _item = new HostEntry();
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
        final String _tmpRedirection;
        if (_cursor.isNull(_cursorIndexOfRedirection)) {
          _tmpRedirection = null;
        } else {
          _tmpRedirection = _cursor.getString(_cursorIndexOfRedirection);
        }
        _item.setRedirection(_tmpRedirection);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ListType getTypeOfHost(final String host) {
    final String _sql = "SELECT `type` FROM `host_entries` WHERE `host` == ? LIMIT 1";
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
      final ListType _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = ListTypeConverter.fromValue(_tmp);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ListType getTypeForHost(final String host) {
    final String _sql = "SELECT IFNULL((SELECT `type` FROM `host_entries` WHERE `host` == ? LIMIT 1), 1)";
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
      final ListType _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = ListTypeConverter.fromValue(_tmp);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public HostEntry getEntry(final String host) {
    final String _sql = "SELECT * FROM `host_entries` WHERE `host` == ? LIMIT 1";
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
      final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfRedirection = CursorUtil.getColumnIndexOrThrow(_cursor, "redirection");
      final HostEntry _result;
      if(_cursor.moveToFirst()) {
        _result = new HostEntry();
        final String _tmpHost;
        if (_cursor.isNull(_cursorIndexOfHost)) {
          _tmpHost = null;
        } else {
          _tmpHost = _cursor.getString(_cursorIndexOfHost);
        }
        _result.setHost(_tmpHost);
        final ListType _tmpType;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfType);
        }
        _tmpType = ListTypeConverter.fromValue(_tmp);
        _result.setType(_tmpType);
        final String _tmpRedirection;
        if (_cursor.isNull(_cursorIndexOfRedirection)) {
          _tmpRedirection = null;
        } else {
          _tmpRedirection = _cursor.getString(_cursorIndexOfRedirection);
        }
        _result.setRedirection(_tmpRedirection);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
