package com.fitflow.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allActivities: Flow<List<Activity>> = workoutDao.getAllActivities()
    val allPlans: Flow<List<Plan>> = workoutDao.getAllPlans()
    val allHistory: Flow<List<HistoryEntry>> = workoutDao.getAllHistory()

    suspend fun insertActivity(activity: Activity) = workoutDao.insertActivity(activity)
    
    suspend fun insertPlan(plan: Plan) = workoutDao.insertPlan(plan)
    
    suspend fun updatePlan(plan: Plan) = workoutDao.updatePlan(plan)

    fun getActivitiesForPlan(planId: Long) = workoutDao.getActivitiesForPlan(planId)

    fun getPlanActivitiesWithDetails(planId: Long) = workoutDao.getPlanActivitiesWithDetails(planId)

    suspend fun insertPlanActivity(planActivity: PlanActivity) = workoutDao.insertPlanActivity(planActivity)

    suspend fun updatePlanActivity(planActivity: PlanActivity) = workoutDao.updatePlanActivity(planActivity)

    suspend fun deletePlanActivity(id: Long) = workoutDao.deletePlanActivity(id)

    suspend fun getActivityById(id: Long) = workoutDao.getActivityById(id)

    fun getScheduledActivitiesForDay(dayOfWeek: Int) = workoutDao.getScheduledActivitiesForDay(dayOfWeek)

    suspend fun insertHistoryEntry(entry: HistoryEntry) = workoutDao.insertHistoryEntry(entry)

    fun getHistoryInRange(start: LocalDate, end: LocalDate): Flow<List<HistoryEntry>> {
        val startDateTime = start.atStartOfDay()
        val endDateTime = end.atTime(23, 59, 59)
        return workoutDao.getHistoryInRange(startDateTime, endDateTime)
    }
}
