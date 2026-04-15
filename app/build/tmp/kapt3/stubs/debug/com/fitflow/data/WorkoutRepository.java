package com.fitflow.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u00062\u0006\u0010\u0018\u001a\u00020\u0014J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cJ\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00070\u00062\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010!\u001a\u00020\"J\u0016\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010+J\u0016\u00100\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010.R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/fitflow/data/WorkoutRepository;", "", "workoutDao", "Lcom/fitflow/data/WorkoutDao;", "(Lcom/fitflow/data/WorkoutDao;)V", "allActivities", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/fitflow/data/Activity;", "getAllActivities", "()Lkotlinx/coroutines/flow/Flow;", "allHistory", "Lcom/fitflow/data/HistoryEntry;", "getAllHistory", "allPlans", "Lcom/fitflow/data/Plan;", "getAllPlans", "deletePlanActivity", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivitiesForPlan", "Lcom/fitflow/data/PlanActivity;", "planId", "getActivityById", "getHistoryInRange", "start", "Ljava/time/LocalDate;", "end", "getPlanActivitiesWithDetails", "Lcom/fitflow/data/PlanActivityWithDetails;", "getScheduledActivitiesForDay", "dayOfWeek", "", "insertActivity", "activity", "(Lcom/fitflow/data/Activity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertHistoryEntry", "entry", "(Lcom/fitflow/data/HistoryEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlan", "plan", "(Lcom/fitflow/data/Plan;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlanActivity", "planActivity", "(Lcom/fitflow/data/PlanActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlan", "updatePlanActivity", "app_debug"})
public final class WorkoutRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.fitflow.data.WorkoutDao workoutDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> allActivities = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Plan>> allPlans = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> allHistory = null;
    
    public WorkoutRepository(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.WorkoutDao workoutDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> getAllActivities() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Plan>> getAllPlans() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> getAllHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlan(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePlan(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.PlanActivity>> getActivitiesForPlan(long planId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.PlanActivityWithDetails>> getPlanActivitiesWithDetails(long planId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlanActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivity planActivity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePlanActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivity planActivity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlanActivity(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getActivityById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.fitflow.data.Activity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> getScheduledActivitiesForDay(int dayOfWeek) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertHistoryEntry(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.HistoryEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> getHistoryInRange(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate start, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate end) {
        return null;
    }
}