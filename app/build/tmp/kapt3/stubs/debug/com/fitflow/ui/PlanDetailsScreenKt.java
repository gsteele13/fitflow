package com.fitflow.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001at\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2$\u0010\f\u001a \u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a:\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a(\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007\u00a8\u0006\u001a"}, d2 = {"ActivityDialog", "", "initialName", "", "initialDescription", "initialDays", "", "", "title", "confirmLabel", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function3;", "PlanActivityItem", "activity", "Lcom/fitflow/data/PlanActivityWithDetails;", "onToggle", "onDelete", "onClick", "PlanDetailsScreen", "planId", "", "onBack", "viewModel", "Lcom/fitflow/ui/WorkoutViewModel;", "app_debug"})
public final class PlanDetailsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void PlanDetailsScreen(long planId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.fitflow.ui.WorkoutViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PlanActivityItem(@org.jetbrains.annotations.NotNull()
    com.fitflow.data.PlanActivityWithDetails activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ActivityDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String initialName, @org.jetbrains.annotations.NotNull()
    java.lang.String initialDescription, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> initialDays, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmLabel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.util.List<java.lang.Integer>, kotlin.Unit> onConfirm) {
    }
}