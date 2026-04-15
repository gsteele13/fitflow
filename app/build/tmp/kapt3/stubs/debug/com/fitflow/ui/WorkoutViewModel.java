package com.fitflow.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0015J\u0016\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013J\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00070\u00062\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0015J\u000e\u0010&\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\"J\u000e\u0010\'\u001a\u00020\u00112\u0006\u0010$\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\fR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/fitflow/ui/WorkoutViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allHistory", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/fitflow/data/HistoryEntry;", "getAllHistory", "()Lkotlinx/coroutines/flow/Flow;", "allPlans", "Lcom/fitflow/data/Plan;", "getAllPlans", "repository", "Lcom/fitflow/data/WorkoutRepository;", "addActivityToPlan", "", "planId", "", "name", "", "description", "dayOfWeek", "", "addNote", "note", "addUnscheduledActivity", "createPlan", "deleteActivityFromPlan", "activityId", "getPlanActivities", "Lcom/fitflow/data/PlanActivityWithDetails;", "getScheduledActivitiesForDay", "Lcom/fitflow/data/Activity;", "markAsDone", "activity", "notes", "skipActivity", "toggleActivityInPlanActive", "togglePlanActive", "plan", "app_debug"})
public final class WorkoutViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitflow.data.WorkoutRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Plan>> allPlans = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> allHistory = null;
    
    public WorkoutViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Plan>> getAllPlans() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.HistoryEntry>> getAllHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.Activity>> getScheduledActivitiesForDay(int dayOfWeek) {
        return null;
    }
    
    public final void markAsDone(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
    }
    
    public final void skipActivity(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Activity activity) {
    }
    
    public final void addNote(@org.jetbrains.annotations.NotNull()
    java.lang.String note) {
    }
    
    public final void createPlan(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void togglePlanActive(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Plan plan) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.fitflow.data.PlanActivityWithDetails>> getPlanActivities(long planId) {
        return null;
    }
    
    public final void addActivityToPlan(long planId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int dayOfWeek) {
    }
    
    public final void toggleActivityInPlanActive(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivityWithDetails activity) {
    }
    
    public final void deleteActivityFromPlan(long activityId) {
    }
    
    public final void addUnscheduledActivity(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
}