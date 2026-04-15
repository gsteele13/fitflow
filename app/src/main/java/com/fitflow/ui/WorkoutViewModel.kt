package com.fitflow.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fitflow.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WorkoutRepository
    val allPlans: Flow<List<Plan>>
    val allHistory: Flow<List<HistoryEntry>>

    init {
        val workoutDao = WorkoutDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(workoutDao)
        allPlans = repository.allPlans
        allHistory = repository.allHistory
    }

    fun getScheduledActivitiesForDay(dayOfWeek: Int): Flow<List<Activity>> {
        return repository.getScheduledActivitiesForDay(dayOfWeek)
    }

    fun markAsDone(activity: Activity, notes: String) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = LocalDateTime.now(),
                type = "ACTIVITY",
                name = activity.name,
                description = activity.description,
                notes = notes,
                status = HistoryStatus.COMPLETED
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun skipActivity(activity: Activity) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = LocalDateTime.now(),
                type = "ACTIVITY",
                name = activity.name,
                description = activity.description,
                status = HistoryStatus.SKIPPED
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun addNote(note: String) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = LocalDateTime.now(),
                type = "NOTE",
                name = "Note",
                description = "",
                notes = note,
                status = HistoryStatus.NOTE
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun createPlan(name: String) {
        viewModelScope.launch {
            repository.insertPlan(Plan(name = name))
        }
    }

    fun togglePlanActive(plan: Plan) {
        viewModelScope.launch {
            repository.updatePlan(plan.copy(isActive = !plan.isActive))
        }
    }

    fun getPlanActivities(planId: Long): Flow<List<PlanActivityWithDetails>> {
        return repository.getPlanActivitiesWithDetails(planId)
    }

    fun addActivityToPlan(planId: Long, name: String, description: String, dayOfWeek: Int) {
        viewModelScope.launch {
            val activityId = repository.insertActivity(Activity(name = name, description = description))
            repository.insertPlanActivity(PlanActivity(planId = planId, activityId = activityId, dayOfWeek = dayOfWeek))
        }
    }

    fun toggleActivityInPlanActive(activity: PlanActivityWithDetails) {
        viewModelScope.launch {
            repository.updatePlanActivity(PlanActivity(
                id = activity.id,
                planId = activity.planId,
                activityId = activity.activityId,
                dayOfWeek = activity.dayOfWeek,
                isActive = !activity.isActive
            ))
        }
    }

    fun deleteActivityFromPlan(activityId: Long) {
        viewModelScope.launch {
            repository.deletePlanActivity(activityId)
        }
    }
    
    fun addUnscheduledActivity(name: String, description: String) {
        viewModelScope.launch {
            // Unscheduled means just adding to history directly as completed or maybe just a one-off?
            // User said "add an unscheduled activity... individual one... or one from plans"
            // If it's unscheduled, maybe it just appears in today's list or goes straight to history.
            // Let's assume it adds to today's "temporary" list if we had one, 
            // but for now let's just create a history entry if they perform it.
        }
    }
}
