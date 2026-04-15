package com.fitflow.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM activities")
    fun getAllActivities(): Flow<List<Activity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity): Long

    @Query("SELECT * FROM plans")
    fun getAllPlans(): Flow<List<Plan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: Plan): Long

    @Update
    suspend fun updatePlan(plan: Plan)

    @Query("SELECT * FROM plan_activities WHERE planId = :planId")
    fun getActivitiesForPlan(planId: Long): Flow<List<PlanActivity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanActivity(planActivity: PlanActivity)

    @Update
    suspend fun updatePlanActivity(planActivity: PlanActivity)

    @Query("""
        SELECT a.*, pa.dayOfWeek, pa.isActive as paIsActive, p.isActive as pIsActive 
        FROM activities a 
        JOIN plan_activities pa ON a.id = pa.activityId 
        JOIN plans p ON pa.planId = p.id 
        WHERE pa.dayOfWeek = :dayOfWeek AND pa.isActive = 1 AND p.isActive = 1
    """)
    fun getScheduledActivitiesForDay(dayOfWeek: Int): Flow<List<Activity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryEntry(entry: HistoryEntry)

    @Query("SELECT * FROM history ORDER BY dateTime DESC")
    fun getAllHistory(): Flow<List<HistoryEntry>>

    @Query("SELECT * FROM history WHERE dateTime BETWEEN :start AND :end ORDER BY dateTime DESC")
    fun getHistoryInRange(start: java.time.LocalDateTime, end: java.time.LocalDateTime): Flow<List<HistoryEntry>>

    @Query("""
        SELECT pa.id, pa.planId, pa.activityId, a.name, a.description, pa.dayOfWeek, pa.isActive 
        FROM plan_activities pa 
        JOIN activities a ON pa.activityId = a.id 
        WHERE pa.planId = :planId
    """)
    fun getPlanActivitiesWithDetails(planId: Long): Flow<List<PlanActivityWithDetails>>

    @Query("SELECT * FROM activities WHERE id = :id")
    suspend fun getActivityById(id: Long): Activity?

    @Query("DELETE FROM plan_activities WHERE id = :id")
    suspend fun deletePlanActivity(id: Long)
}
