package com.fitflow.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aN\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001az\u0010\u000e\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000b2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0007\u001a\u0018\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\u001a\u0012\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007\u001a2\u0010\u001c\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u0018H\u0007\u00a8\u0006\u001e"}, d2 = {"ActivityItem", "", "name", "", "onClick", "Lkotlin/Function0;", "ActivityOptionsDialog", "activity", "Lcom/fitflow/data/Activity;", "onDismiss", "onDone", "Lkotlin/Function1;", "onSkip", "onSnooze", "AddActivityOrNoteDialog", "onAddNote", "onAddUnscheduled", "Lkotlin/Function2;", "onAddFromPlan", "onPlanAdHoc", "availableActivities", "", "DaySchedule", "date", "Ljava/time/LocalDate;", "viewModel", "Lcom/fitflow/ui/WorkoutViewModel;", "ScheduleScreen", "SnoozeDialog", "initialDate", "app_debug"})
public final class ScheduleScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ScheduleScreen(@org.jetbrains.annotations.NotNull()
    com.fitflow.ui.WorkoutViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DaySchedule(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    com.fitflow.ui.WorkoutViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SnoozeDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.time.LocalDate, kotlin.Unit> onSnooze, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate initialDate) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ActivityItem(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ActivityOptionsDialog(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDone, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSkip, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSnooze) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddActivityOrNoteDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAddNote, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onAddUnscheduled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.fitflow.data.Activity, kotlin.Unit> onAddFromPlan, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.fitflow.data.Activity, kotlin.Unit> onPlanAdHoc, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitflow.data.Activity> availableActivities) {
    }
}