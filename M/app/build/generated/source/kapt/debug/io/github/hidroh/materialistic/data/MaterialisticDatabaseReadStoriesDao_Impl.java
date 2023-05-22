package io.github.hidroh.materialistic.data;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MaterialisticDatabaseReadStoriesDao_Impl implements MaterialisticDatabase.ReadStoriesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MaterialisticDatabase.ReadStory> __insertionAdapterOfReadStory;

  public MaterialisticDatabaseReadStoriesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReadStory = new EntityInsertionAdapter<MaterialisticDatabase.ReadStory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `read` (`_id`,`itemid`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MaterialisticDatabase.ReadStory value) {
        stmt.bindLong(1, value.getId());
        if (value.getItemId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getItemId());
        }
      }
    };
  }

  @Override
  public void insert(final MaterialisticDatabase.ReadStory readStory) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReadStory.insert(readStory);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public MaterialisticDatabase.ReadStory selectByItemId(final String itemId) {
    final String _sql = "SELECT * FROM read WHERE itemid = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (itemId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemid");
      final MaterialisticDatabase.ReadStory _result;
      if(_cursor.moveToFirst()) {
        final String _tmpItemId;
        if (_cursor.isNull(_cursorIndexOfItemId)) {
          _tmpItemId = null;
        } else {
          _tmpItemId = _cursor.getString(_cursorIndexOfItemId);
        }
        _result = new MaterialisticDatabase.ReadStory(_tmpItemId);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
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
