package com.fitflow.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/fitflow/data/WorkoutDatabase;", "Landroidx/room/RoomDatabase;", "()V", "workoutDao", "Lcom/fitflow/data/WorkoutDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.fitflow.data.Activity.class, com.fitflow.data.Plan.class, com.fitflow.data.PlanActivity.class, com.fitflow.data.HistoryEntry.class, com.fitflow.data.PlanSnapshot.class, com.fitflow.data.PlanActivitySnapshot.class, com.fitflow.data.AdHocActivity.class}, version = 4, exportSchema = false)
@androidx.room.TypeConverters(value = {com.fitflow.data.Converters.class})
public abstract class WorkoutDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.fitflow.data.WorkoutDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_2_3 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.fitflow.data.WorkoutDatabase.Companion Companion = null;
    
    public WorkoutDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.fitflow.data.WorkoutDao workoutDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/fitflow/data/WorkoutDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/fitflow/data/WorkoutDatabase;", "MIGRATION_2_3", "Landroidx/room/migration/Migration;", "getMIGRATION_2_3", "()Landroidx/room/migration/Migration;", "MIGRATION_3_4", "getMIGRATION_3_4", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_2_3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_3_4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitflow.data.WorkoutDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}