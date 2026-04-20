package com.fitflow.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkoutDao_Impl implements WorkoutDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Activity> __insertionAdapterOfActivity;

  private final EntityInsertionAdapter<Plan> __insertionAdapterOfPlan;

  private final EntityInsertionAdapter<PlanActivity> __insertionAdapterOfPlanActivity;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<AdHocActivity> __insertionAdapterOfAdHocActivity;

  private final EntityInsertionAdapter<HistoryEntry> __insertionAdapterOfHistoryEntry;

  private final EntityInsertionAdapter<PlanSnapshot> __insertionAdapterOfPlanSnapshot;

  private final EntityInsertionAdapter<PlanActivitySnapshot> __insertionAdapterOfPlanActivitySnapshot;

  private final EntityDeletionOrUpdateAdapter<HistoryEntry> __deletionAdapterOfHistoryEntry;

  private final EntityDeletionOrUpdateAdapter<Plan> __updateAdapterOfPlan;

  private final EntityDeletionOrUpdateAdapter<PlanActivity> __updateAdapterOfPlanActivity;

  private final EntityDeletionOrUpdateAdapter<HistoryEntry> __updateAdapterOfHistoryEntry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAdHocActivity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlan;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlanActivity;

  public WorkoutDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActivity = new EntityInsertionAdapter<Activity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `activities` (`id`,`name`,`description`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Activity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
      }
    };
    this.__insertionAdapterOfPlan = new EntityInsertionAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plans` (`id`,`name`,`isActive`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
      }
    };
    this.__insertionAdapterOfPlanActivity = new EntityInsertionAdapter<PlanActivity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plan_activities` (`id`,`planId`,`activityId`,`daysOfWeek`,`isActive`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlanActivity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlanId());
        statement.bindLong(3, entity.getActivityId());
        final String _tmp = __converters.fromIntList(entity.getDaysOfWeek());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
      }
    };
    this.__insertionAdapterOfAdHocActivity = new EntityInsertionAdapter<AdHocActivity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `ad_hoc_activities` (`id`,`activityId`,`scheduledDate`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AdHocActivity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getActivityId());
        final Long _tmp = __converters.dateToTimestamp(entity.getScheduledDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
      }
    };
    this.__insertionAdapterOfHistoryEntry = new EntityInsertionAdapter<HistoryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `history` (`id`,`dateTime`,`type`,`name`,`description`,`notes`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoryEntry entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __converters.dateToTimestamp(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNotes());
        }
        final String _tmp_1 = __converters.fromStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
      }
    };
    this.__insertionAdapterOfPlanSnapshot = new EntityInsertionAdapter<PlanSnapshot>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plan_snapshots` (`id`,`originalPlanId`,`planName`,`snapshotDate`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlanSnapshot entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getOriginalPlanId());
        if (entity.getPlanName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPlanName());
        }
        final Long _tmp = __converters.dateToTimestamp(entity.getSnapshotDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
      }
    };
    this.__insertionAdapterOfPlanActivitySnapshot = new EntityInsertionAdapter<PlanActivitySnapshot>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plan_activity_snapshots` (`id`,`planSnapshotId`,`activityName`,`activityDescription`,`daysOfWeek`,`isActive`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlanActivitySnapshot entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlanSnapshotId());
        if (entity.getActivityName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getActivityName());
        }
        if (entity.getActivityDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getActivityDescription());
        }
        final String _tmp = __converters.fromIntList(entity.getDaysOfWeek());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp);
        }
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
      }
    };
    this.__deletionAdapterOfHistoryEntry = new EntityDeletionOrUpdateAdapter<HistoryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `history` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoryEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPlan = new EntityDeletionOrUpdateAdapter<Plan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `plans` SET `id` = ?,`name` = ?,`isActive` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Plan entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getId());
      }
    };
    this.__updateAdapterOfPlanActivity = new EntityDeletionOrUpdateAdapter<PlanActivity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `plan_activities` SET `id` = ?,`planId` = ?,`activityId` = ?,`daysOfWeek` = ?,`isActive` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlanActivity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlanId());
        statement.bindLong(3, entity.getActivityId());
        final String _tmp = __converters.fromIntList(entity.getDaysOfWeek());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getId());
      }
    };
    this.__updateAdapterOfHistoryEntry = new EntityDeletionOrUpdateAdapter<HistoryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `history` SET `id` = ?,`dateTime` = ?,`type` = ?,`name` = ?,`description` = ?,`notes` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoryEntry entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __converters.dateToTimestamp(entity.getDateTime());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNotes());
        }
        final String _tmp_1 = __converters.fromStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAdHocActivity = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ad_hoc_activities WHERE activityId = ? AND scheduledDate BETWEEN ? AND ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePlan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM plans WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePlanActivity = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM plan_activities WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertActivity(final Activity activity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfActivity.insertAndReturnId(activity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPlan(final Plan plan, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlan.insertAndReturnId(plan);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPlanActivity(final PlanActivity planActivity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlanActivity.insert(planActivity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAdHocActivity(final AdHocActivity adHocActivity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAdHocActivity.insert(adHocActivity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertHistoryEntry(final HistoryEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistoryEntry.insert(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPlanSnapshot(final PlanSnapshot snapshot,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlanSnapshot.insertAndReturnId(snapshot);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPlanActivitySnapshots(final List<PlanActivitySnapshot> snapshots,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlanActivitySnapshot.insert(snapshots);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHistoryEntry(final HistoryEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHistoryEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePlan(final Plan plan, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlan.handle(plan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePlanActivity(final PlanActivity planActivity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlanActivity.handle(planActivity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateHistoryEntry(final HistoryEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHistoryEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAdHocActivity(final long activityId, final LocalDateTime startOfDay,
      final LocalDateTime endOfDay, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAdHocActivity.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, activityId);
        _argIndex = 2;
        final Long _tmp = __converters.dateToTimestamp(startOfDay);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 3;
        final Long _tmp_1 = __converters.dateToTimestamp(endOfDay);
        if (_tmp_1 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp_1);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAdHocActivity.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlan(final long planId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlan.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, planId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePlan.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlanActivity(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlanActivity.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePlanActivity.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Activity>> getAllActivities() {
    final String _sql = "SELECT * FROM activities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"activities"}, new Callable<List<Activity>>() {
      @Override
      @NonNull
      public List<Activity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<Activity> _result = new ArrayList<Activity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Activity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new Activity(_tmpId,_tmpName,_tmpDescription);
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
  public Flow<List<Plan>> getAllPlans() {
    final String _sql = "SELECT * FROM plans";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plans"}, new Callable<List<Plan>>() {
      @Override
      @NonNull
      public List<Plan> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<Plan> _result = new ArrayList<Plan>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Plan _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _item = new Plan(_tmpId,_tmpName,_tmpIsActive);
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
  public Flow<List<PlanActivity>> getActivitiesForPlan(final long planId) {
    final String _sql = "SELECT * FROM plan_activities WHERE planId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plan_activities"}, new Callable<List<PlanActivity>>() {
      @Override
      @NonNull
      public List<PlanActivity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "planId");
          final int _cursorIndexOfActivityId = CursorUtil.getColumnIndexOrThrow(_cursor, "activityId");
          final int _cursorIndexOfDaysOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "daysOfWeek");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<PlanActivity> _result = new ArrayList<PlanActivity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlanActivity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPlanId;
            _tmpPlanId = _cursor.getLong(_cursorIndexOfPlanId);
            final long _tmpActivityId;
            _tmpActivityId = _cursor.getLong(_cursorIndexOfActivityId);
            final List<Integer> _tmpDaysOfWeek;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDaysOfWeek)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDaysOfWeek);
            }
            _tmpDaysOfWeek = __converters.toIntList(_tmp);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new PlanActivity(_tmpId,_tmpPlanId,_tmpActivityId,_tmpDaysOfWeek,_tmpIsActive);
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
  public Flow<List<Activity>> getScheduledActivitiesForDay(final int dayOfWeek,
      final LocalDateTime startOfDay, final LocalDateTime endOfDay) {
    final String _sql = "\n"
            + "        SELECT a.* \n"
            + "        FROM activities a \n"
            + "        JOIN plan_activities pa ON a.id = pa.activityId \n"
            + "        JOIN plans p ON pa.planId = p.id \n"
            + "        WHERE pa.daysOfWeek LIKE '%' || ? || '%' AND pa.isActive = 1 AND p.isActive = 1\n"
            + "        AND a.name NOT IN (\n"
            + "            SELECT h.name FROM history h \n"
            + "            WHERE h.status IN ('COMPLETED', 'SKIPPED') \n"
            + "            AND h.dateTime BETWEEN ? AND ?\n"
            + "        )\n"
            + "        UNION\n"
            + "        SELECT a.*\n"
            + "        FROM activities a\n"
            + "        JOIN ad_hoc_activities aha ON a.id = aha.activityId\n"
            + "        WHERE aha.scheduledDate BETWEEN ? AND ?\n"
            + "        AND a.name NOT IN (\n"
            + "            SELECT h.name FROM history h \n"
            + "            WHERE h.status IN ('COMPLETED', 'SKIPPED') \n"
            + "            AND h.dateTime BETWEEN ? AND ?\n"
            + "        )\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 7);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayOfWeek);
    _argIndex = 2;
    final Long _tmp = __converters.dateToTimestamp(startOfDay);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 3;
    final Long _tmp_1 = __converters.dateToTimestamp(endOfDay);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    _argIndex = 4;
    final Long _tmp_2 = __converters.dateToTimestamp(startOfDay);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_2);
    }
    _argIndex = 5;
    final Long _tmp_3 = __converters.dateToTimestamp(endOfDay);
    if (_tmp_3 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_3);
    }
    _argIndex = 6;
    final Long _tmp_4 = __converters.dateToTimestamp(startOfDay);
    if (_tmp_4 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_4);
    }
    _argIndex = 7;
    final Long _tmp_5 = __converters.dateToTimestamp(endOfDay);
    if (_tmp_5 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_5);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"activities", "plan_activities",
        "plans", "history", "ad_hoc_activities"}, new Callable<List<Activity>>() {
      @Override
      @NonNull
      public List<Activity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<Activity> _result = new ArrayList<Activity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Activity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new Activity(_tmpId,_tmpName,_tmpDescription);
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
  public Object getPlanById(final long planId, final Continuation<? super Plan> $completion) {
    final String _sql = "SELECT * FROM plans WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Plan>() {
      @Override
      @Nullable
      public Plan call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final Plan _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _result = new Plan(_tmpId,_tmpName,_tmpIsActive);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPlanActivitiesByPlanId(final long planId,
      final Continuation<? super List<PlanActivity>> $completion) {
    final String _sql = "SELECT * FROM plan_activities WHERE planId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PlanActivity>>() {
      @Override
      @NonNull
      public List<PlanActivity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "planId");
          final int _cursorIndexOfActivityId = CursorUtil.getColumnIndexOrThrow(_cursor, "activityId");
          final int _cursorIndexOfDaysOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "daysOfWeek");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<PlanActivity> _result = new ArrayList<PlanActivity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlanActivity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPlanId;
            _tmpPlanId = _cursor.getLong(_cursorIndexOfPlanId);
            final long _tmpActivityId;
            _tmpActivityId = _cursor.getLong(_cursorIndexOfActivityId);
            final List<Integer> _tmpDaysOfWeek;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDaysOfWeek)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDaysOfWeek);
            }
            _tmpDaysOfWeek = __converters.toIntList(_tmp);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new PlanActivity(_tmpId,_tmpPlanId,_tmpActivityId,_tmpDaysOfWeek,_tmpIsActive);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HistoryEntry>> getAllHistory() {
    final String _sql = "SELECT * FROM history ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"history"}, new Callable<List<HistoryEntry>>() {
      @Override
      @NonNull
      public List<HistoryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<HistoryEntry> _result = new ArrayList<HistoryEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoryEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final LocalDateTime _tmpDateTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateTime);
            }
            _tmpDateTime = __converters.fromTimestamp(_tmp);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final HistoryStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toStatus(_tmp_1);
            _item = new HistoryEntry(_tmpId,_tmpDateTime,_tmpType,_tmpName,_tmpDescription,_tmpNotes,_tmpStatus);
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
  public Flow<List<HistoryEntry>> getHistoryInRange(final LocalDateTime start,
      final LocalDateTime end) {
    final String _sql = "SELECT * FROM history WHERE dateTime BETWEEN ? AND ? ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __converters.dateToTimestamp(start);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __converters.dateToTimestamp(end);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"history"}, new Callable<List<HistoryEntry>>() {
      @Override
      @NonNull
      public List<HistoryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<HistoryEntry> _result = new ArrayList<HistoryEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoryEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final LocalDateTime _tmpDateTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfDateTime);
            }
            _tmpDateTime = __converters.fromTimestamp(_tmp_2);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final HistoryStatus _tmpStatus;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toStatus(_tmp_3);
            _item = new HistoryEntry(_tmpId,_tmpDateTime,_tmpType,_tmpName,_tmpDescription,_tmpNotes,_tmpStatus);
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
  public Flow<List<PlanActivityWithDetails>> getPlanActivitiesWithDetails(final long planId) {
    final String _sql = "\n"
            + "        SELECT pa.id, pa.planId, pa.activityId, a.name, a.description, pa.daysOfWeek, pa.isActive\n"
            + "        FROM plan_activities pa \n"
            + "        JOIN activities a ON pa.activityId = a.id \n"
            + "        WHERE pa.planId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, planId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plan_activities",
        "activities"}, new Callable<List<PlanActivityWithDetails>>() {
      @Override
      @NonNull
      public List<PlanActivityWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfPlanId = 1;
          final int _cursorIndexOfActivityId = 2;
          final int _cursorIndexOfName = 3;
          final int _cursorIndexOfDescription = 4;
          final int _cursorIndexOfDaysOfWeek = 5;
          final int _cursorIndexOfIsActive = 6;
          final List<PlanActivityWithDetails> _result = new ArrayList<PlanActivityWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlanActivityWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPlanId;
            _tmpPlanId = _cursor.getLong(_cursorIndexOfPlanId);
            final long _tmpActivityId;
            _tmpActivityId = _cursor.getLong(_cursorIndexOfActivityId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final List<Integer> _tmpDaysOfWeek;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDaysOfWeek)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDaysOfWeek);
            }
            _tmpDaysOfWeek = __converters.toIntList(_tmp);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new PlanActivityWithDetails(_tmpId,_tmpPlanId,_tmpActivityId,_tmpName,_tmpDescription,_tmpDaysOfWeek,_tmpIsActive);
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
  public Object getActivityById(final long id, final Continuation<? super Activity> $completion) {
    final String _sql = "SELECT * FROM activities WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Activity>() {
      @Override
      @Nullable
      public Activity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final Activity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _result = new Activity(_tmpId,_tmpName,_tmpDescription);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Activity>> getActivitiesInPlans() {
    final String _sql = "\n"
            + "        SELECT DISTINCT a.* \n"
            + "        FROM activities a \n"
            + "        JOIN plan_activities pa ON a.id = pa.activityId \n"
            + "        JOIN plans p ON pa.planId = p.id\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"activities", "plan_activities",
        "plans"}, new Callable<List<Activity>>() {
      @Override
      @NonNull
      public List<Activity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<Activity> _result = new ArrayList<Activity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Activity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new Activity(_tmpId,_tmpName,_tmpDescription);
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
  public Flow<List<PlanSnapshot>> getAllPlanSnapshots() {
    final String _sql = "SELECT * FROM plan_snapshots ORDER BY snapshotDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plan_snapshots"}, new Callable<List<PlanSnapshot>>() {
      @Override
      @NonNull
      public List<PlanSnapshot> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalPlanId = CursorUtil.getColumnIndexOrThrow(_cursor, "originalPlanId");
          final int _cursorIndexOfPlanName = CursorUtil.getColumnIndexOrThrow(_cursor, "planName");
          final int _cursorIndexOfSnapshotDate = CursorUtil.getColumnIndexOrThrow(_cursor, "snapshotDate");
          final List<PlanSnapshot> _result = new ArrayList<PlanSnapshot>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlanSnapshot _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalPlanId;
            _tmpOriginalPlanId = _cursor.getLong(_cursorIndexOfOriginalPlanId);
            final String _tmpPlanName;
            if (_cursor.isNull(_cursorIndexOfPlanName)) {
              _tmpPlanName = null;
            } else {
              _tmpPlanName = _cursor.getString(_cursorIndexOfPlanName);
            }
            final LocalDateTime _tmpSnapshotDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfSnapshotDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfSnapshotDate);
            }
            _tmpSnapshotDate = __converters.fromTimestamp(_tmp);
            _item = new PlanSnapshot(_tmpId,_tmpOriginalPlanId,_tmpPlanName,_tmpSnapshotDate);
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
  public Object getActivitiesForSnapshot(final long snapshotId,
      final Continuation<? super List<PlanActivitySnapshot>> $completion) {
    final String _sql = "SELECT * FROM plan_activity_snapshots WHERE planSnapshotId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, snapshotId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PlanActivitySnapshot>>() {
      @Override
      @NonNull
      public List<PlanActivitySnapshot> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlanSnapshotId = CursorUtil.getColumnIndexOrThrow(_cursor, "planSnapshotId");
          final int _cursorIndexOfActivityName = CursorUtil.getColumnIndexOrThrow(_cursor, "activityName");
          final int _cursorIndexOfActivityDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "activityDescription");
          final int _cursorIndexOfDaysOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "daysOfWeek");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<PlanActivitySnapshot> _result = new ArrayList<PlanActivitySnapshot>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlanActivitySnapshot _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPlanSnapshotId;
            _tmpPlanSnapshotId = _cursor.getLong(_cursorIndexOfPlanSnapshotId);
            final String _tmpActivityName;
            if (_cursor.isNull(_cursorIndexOfActivityName)) {
              _tmpActivityName = null;
            } else {
              _tmpActivityName = _cursor.getString(_cursorIndexOfActivityName);
            }
            final String _tmpActivityDescription;
            if (_cursor.isNull(_cursorIndexOfActivityDescription)) {
              _tmpActivityDescription = null;
            } else {
              _tmpActivityDescription = _cursor.getString(_cursorIndexOfActivityDescription);
            }
            final List<Integer> _tmpDaysOfWeek;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDaysOfWeek)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDaysOfWeek);
            }
            _tmpDaysOfWeek = __converters.toIntList(_tmp);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item = new PlanActivitySnapshot(_tmpId,_tmpPlanSnapshotId,_tmpActivityName,_tmpActivityDescription,_tmpDaysOfWeek,_tmpIsActive);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHistoryEntries(final List<Long> ids,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM history WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlanSnapshots(final List<Long> ids,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM plan_snapshots WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlanActivitySnapshots(final List<Long> snapshotIds,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM plan_activity_snapshots WHERE planSnapshotId IN (");
        final int _inputSize = snapshotIds.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : snapshotIds) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
