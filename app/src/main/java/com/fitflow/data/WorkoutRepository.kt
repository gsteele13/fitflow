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

    fun getHistoryInRange(start: LocalDate, end: LocalDate): Flow<List<HistoryEntry>> {
        val startDateTime = start.atStartOfDay()
        val endDateTime = end.atTime(23, 59, 59)
        return workoutDao.getHistoryInRange(startDateTime, endDateTime)
    }
}
