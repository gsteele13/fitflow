package com.fitflow.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0005H\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\bH\'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\bH\'J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\t0\bH\'J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\'J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\t0\b2\u0006\u0010\u000b\u001a\u00020\u0005H\'J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\b2\u0006\u0010\u001a\u001a\u00020\u001bH\'J\u0016\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010$J\u0016\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\'\u00a8\u0006*"}, d2 = {"Lcom/fitflow/data/WorkoutDao;", "", "deletePlanActivity", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivitiesForPlan", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/fitflow/data/PlanActivity;", "planId", "getActivityById", "Lcom/fitflow/data/Activity;", "getAllActivities", "getAllHistory", "Lcom/fitflow/data/HistoryEntry;", "getAllPlans", "Lcom/fitflow/data/Plan;", "getHistoryInRange", "start", "Ljava/time/LocalDateTime;", "end", "getPlanActivitiesWithDetails", "Lcom/fitflow/data/PlanActivityWithDetails;", "getScheduledActivitiesForDay", "dayOfWeek", "", "insertActivity", "activity", "(Lcom/fitflow/data/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertHistoryEntry", "entry", "(Lcom/fitflow/data/HistoryEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlan", "plan", "(Lcom/fitflow/data/Plan;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlanActivity", "planActivity", "(Lcom/fitflow/data/PlanActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlan", "updatePlanActivity", "app_debug"})
@androidx.room.Dao()
public abstract interface WorkoutDao {
    
    @androidx.room.Query(value = "SELECT * FROM activities")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> getAllActivities();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM plans")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Plan>> getAllPlans();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPlan(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePlan(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM plan_activities WHERE planId = :planId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.PlanActivity>> getActivitiesForPlan(long planId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPlanActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivity planActivity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePlanActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivity planActivity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT a.*, pa.dayOfWeek, pa.isActive as paIsActive, p.isActive as pIsActive \n        FROM activities a \n        JOIN plan_activities pa ON a.id = pa.activityId \n        JOIN plans p ON pa.planId = p.id \n        WHERE pa.dayOfWeek = :dayOfWeek AND pa.isActive = 1 AND p.isActive = 1\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> getScheduledActivitiesForDay(int dayOfWeek);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertHistoryEntry(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.HistoryEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM history ORDER BY dateTime DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> getAllHistory();
    
    @androidx.room.Query(value = "SELECT * FROM history WHERE dateTime BETWEEN :start AND :end ORDER BY dateTime DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> getHistoryInRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime start, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime end);
    
    @androidx.room.Query(value = "\n        SELECT pa.id, pa.planId, pa.activityId, a.name, a.description, pa.dayOfWeek, pa.isActive \n        FROM plan_activities pa \n        JOIN activities a ON pa.activityId = a.id \n        WHERE pa.planId = :planId\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.PlanActivityWithDetails>> getPlanActivitiesWithDetails(long planId);
    
    @androidx.room.Query(value = "SELECT * FROM activities WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActivityById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitflow.data.Activity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM plan_activities WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePlanActivity(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}