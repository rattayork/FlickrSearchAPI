package com.nristekk.app.flickrsearchapi.data.entity;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
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
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoryDao_Impl implements HistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<History> __insertionAdapterOfHistory;

  private final EntityDeletionOrUpdateAdapter<History> __deletionAdapterOfHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public HistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `history` (`keyword`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        if (value.getKeyword() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getKeyword());
        }
      }
    };
    this.__deletionAdapterOfHistory = new EntityDeletionOrUpdateAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `history` WHERE `keyword` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        if (value.getKeyword() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getKeyword());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM history";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final History history, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistory.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object delete(final History history, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHistory.handle(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Flow<List<History>> getListFlow() {
    final String _sql = "SELECT * FROM history ORDER BY keyword ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"history"}, new Callable<List<History>>() {
      @Override
      public List<History> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
          final List<History> _result = new ArrayList<History>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final History _item;
            final String _tmpKeyword;
            if (_cursor.isNull(_cursorIndexOfKeyword)) {
              _tmpKeyword = null;
            } else {
              _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
            }
            _item = new History(_tmpKeyword);
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
  public Object getList(final Continuation<? super List<History>> p0) {
    final String _sql = "SELECT * FROM history ORDER BY keyword ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<History>>() {
      @Override
      public List<History> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
          final List<History> _result = new ArrayList<History>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final History _item;
            final String _tmpKeyword;
            if (_cursor.isNull(_cursorIndexOfKeyword)) {
              _tmpKeyword = null;
            } else {
              _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
            }
            _item = new History(_tmpKeyword);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
