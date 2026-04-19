package com.fitflow.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

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
        SELECT a.* 
        FROM activities a 
        JOIN plan_activities pa ON a.id = pa.activityId 
        JOIN plans p ON pa.planId = p.id 
        WHERE pa.daysOfWeek LIKE '%' || :dayOfWeek || '%' AND pa.isActive = 1 AND p.isActive = 1
        AND a.name NOT IN (
            SELECT h.name FROM history h 
            WHERE h.status IN ('COMPLETED', 'SKIPPED') 
            AND h.dateTime BETWEEN :startOfDay AND :endOfDay
        )
    """)
    fun getScheduledActivitiesForDay(dayOfWeek: Int, startOfDay: LocalDateTime, endOfDay: LocalDateTime): Flow<List<Activity>>

    @Query("DELETE FROM plans WHERE id = :planId")
    suspend fun deletePlan(planId: Long)

    @Query("SELECT * FROM plans WHERE id = :planId")
    suspend fun getPlanById(planId: Long): Plan?

    @Query("SELECT * FROM plan_activities WHERE planId = :planId")
    suspend fun getPlanActivitiesByPlanId(planId: Long): List<PlanActivity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryEntry(entry: HistoryEntry)

    @Update
    suspend fun updateHistoryEntry(entry: HistoryEntry)

    @Delete
    suspend fun deleteHistoryEntry(entry: HistoryEntry)

    @Query("DELETE FROM history WHERE id IN (:ids)")
    suspend fun deleteHistoryEntries(ids: List<Long>)

    @Query("SELECT * FROM history ORDER BY dateTime DESC")
    fun getAllHistory(): Flow<List<HistoryEntry>>

    @Query("SELECT * FROM history WHERE dateTime BETWEEN :start AND :end ORDER BY dateTime DESC")
    fun getHistoryInRange(start: java.time.LocalDateTime, end: java.time.LocalDateTime): Flow<List<HistoryEntry>>

    @Query("""
        SELECT pa.id, pa.planId, pa.activityId, a.name, a.description, pa.daysOfWeek, pa.isActive
        FROM plan_activities pa 
        JOIN activities a ON pa.activityId = a.id 
        WHERE pa.planId = :planId
    """)
    fun getPlanActivitiesWithDetails(planId: Long): Flow<List<PlanActivityWithDetails>>

    @Query("SELECT * FROM activities WHERE id = :id")
    suspend fun getActivityById(id: Long): Activity?

    @Query("DELETE FROM plan_activities WHERE id = :id")
    suspend fun deletePlanActivity(id: Long)

    @Query("""
        SELECT DISTINCT a.* 
        FROM activities a 
        JOIN plan_activities pa ON a.id = pa.activityId 
        JOIN plans p ON pa.planId = p.id
    """)
    fun getActivitiesInPlans(): Flow<List<Activity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanSnapshot(snapshot: PlanSnapshot): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanActivitySnapshots(snapshots: List<PlanActivitySnapshot>)

    @Query("SELECT * FROM plan_snapshots ORDER BY snapshotDate DESC")
    fun getAllPlanSnapshots(): Flow<List<PlanSnapshot>>

    @Query("SELECT * FROM plan_activity_snapshots WHERE planSnapshotId = :snapshotId")
    suspend fun getActivitiesForSnapshot(snapshotId: Long): List<PlanActivitySnapshot>

    @Query("DELETE FROM plan_snapshots WHERE id IN (:ids)")
    suspend fun deletePlanSnapshots(ids: List<Long>)

    @Query("DELETE FROM plan_activity_snapshots WHERE planSnapshotId IN (:snapshotIds)")
    suspend fun deletePlanActivitySnapshots(snapshotIds: List<Long>)
}
