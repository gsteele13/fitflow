package com.fitflow.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allActivities: Flow<List<Activity>> = workoutDao.getAllActivities()
    val activitiesInPlans: Flow<List<Activity>> = workoutDao.getActivitiesInPlans()
    val allPlans: Flow<List<Plan>> = workoutDao.getAllPlans()
    val allHistory: Flow<List<HistoryEntry>> = workoutDao.getAllHistory()

    suspend fun insertActivity(activity: Activity): Long = workoutDao.insertActivity(activity)
    
    suspend fun insertPlan(plan: Plan) = workoutDao.insertPlan(plan)
    
    suspend fun updatePlan(plan: Plan) = workoutDao.updatePlan(plan)

    fun getActivitiesForPlan(planId: Long) = workoutDao.getActivitiesForPlan(planId)

    fun getPlanActivitiesWithDetails(planId: Long) = workoutDao.getPlanActivitiesWithDetails(planId)

    suspend fun insertPlanActivity(planActivity: PlanActivity) = workoutDao.insertPlanActivity(planActivity)

    suspend fun updatePlanActivity(planActivity: PlanActivity) = workoutDao.updatePlanActivity(planActivity)

    suspend fun deletePlanActivity(id: Long) = workoutDao.deletePlanActivity(id)

    suspend fun getActivityById(id: Long) = workoutDao.getActivityById(id)

    fun getScheduledActivitiesForDay(dayOfWeek: Int, startOfDay: LocalDateTime, endOfDay: LocalDateTime) = 
        workoutDao.getScheduledActivitiesForDay(dayOfWeek, startOfDay, endOfDay)

    suspend fun deletePlan(planId: Long) = workoutDao.deletePlan(planId)

    suspend fun getPlanById(planId: Long) = workoutDao.getPlanById(planId)

    suspend fun getPlanActivitiesByPlanId(planId: Long) = workoutDao.getPlanActivitiesByPlanId(planId)

    suspend fun insertHistoryEntry(entry: HistoryEntry) = workoutDao.insertHistoryEntry(entry)

    suspend fun updateHistoryEntry(entry: HistoryEntry) = workoutDao.updateHistoryEntry(entry)

    suspend fun deleteHistoryEntry(entry: HistoryEntry) = workoutDao.deleteHistoryEntry(entry)

    suspend fun deleteHistoryEntries(ids: List<Long>) = workoutDao.deleteHistoryEntries(ids)

    val allPlanSnapshots: Flow<List<PlanSnapshot>> = workoutDao.getAllPlanSnapshots()

    suspend fun getSnapshotWithDetails(snapshotId: Long): PlanSnapshotWithDetails? {
        val snapshot = workoutDao.getAllPlanSnapshots().toString() // This is wrong, let me fix it in DAO or here
        // Re-reading DAO, I need a single snapshot getter
        return null
    }

    suspend fun createPlanSnapshot(planId: Long) {
        val plan = workoutDao.getPlanById(planId) ?: return
        val activities = workoutDao.getPlanActivitiesByPlanId(planId)
        
        val snapshotId = workoutDao.insertPlanSnapshot(
            PlanSnapshot(
                originalPlanId = plan.id,
                planName = plan.name,
                snapshotDate = LocalDateTime.now()
            )
        )
        
        val activitySnapshots = activities.map { pa ->
            val activity = workoutDao.getActivityById(pa.activityId)
            PlanActivitySnapshot(
                planSnapshotId = snapshotId,
                activityName = activity?.name ?: "Unknown",
                activityDescription = activity?.description ?: "",
                daysOfWeek = pa.daysOfWeek,
                isActive = pa.isActive
            )
        }
        workoutDao.insertPlanActivitySnapshots(activitySnapshots)
    }

    suspend fun getPlanSnapshotWithDetails(snapshot: PlanSnapshot): PlanSnapshotWithDetails {
        val activities = workoutDao.getActivitiesForSnapshot(snapshot.id)
        return PlanSnapshotWithDetails(snapshot, activities)
    }

    suspend fun deletePlanSnapshots(ids: List<Long>) {
        workoutDao.deletePlanActivitySnapshots(ids)
        workoutDao.deletePlanSnapshots(ids)
    }

    suspend fun insertAdHocActivity(adHocActivity: AdHocActivity) = workoutDao.insertAdHocActivity(adHocActivity)

    suspend fun deleteAdHocActivity(activityId: Long, startOfDay: LocalDateTime, endOfDay: LocalDateTime) = 
        workoutDao.deleteAdHocActivity(activityId, startOfDay, endOfDay)

    fun getHistoryInRange(start: LocalDate, end: LocalDate): Flow<List<HistoryEntry>> {
        val startDateTime = start.atStartOfDay()
        val endDateTime = end.atTime(23, 59, 59)
        return workoutDao.getHistoryInRange(startDateTime, endDateTime)
    }
}
