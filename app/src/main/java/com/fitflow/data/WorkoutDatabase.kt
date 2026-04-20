package com.fitflow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Activity::class, Plan::class, PlanActivity::class, HistoryEntry::class, PlanSnapshot::class, PlanActivitySnapshot::class, AdHocActivity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `plan_snapshots` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `originalPlanId` INTEGER NOT NULL, 
                        `planName` TEXT NOT NULL, 
                        `snapshotDate` INTEGER NOT NULL
                    )
                """)
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `plan_activity_snapshots` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `planSnapshotId` INTEGER NOT NULL, 
                        `activityName` TEXT NOT NULL, 
                        `activityDescription` TEXT NOT NULL, 
                        `daysOfWeek` TEXT NOT NULL, 
                        `isActive` INTEGER NOT NULL
                    )
                """)
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `ad_hoc_activities` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `activityId` INTEGER NOT NULL, 
                        `scheduledDate` INTEGER NOT NULL
                    )
                """)
            }
        }

        fun getDatabase(context: Context): WorkoutDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDatabase::class.java,
                    "workout_database"
                )
                .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
