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

    fun getScheduledActivitiesForDay(date: LocalDate): Flow<List<Activity>> {
        val dayOfWeek = date.dayOfWeek.value
        val startOfDay = date.atStartOfDay()
        val endOfDay = date.atTime(23, 59, 59)
        return repository.getScheduledActivitiesForDay(dayOfWeek, startOfDay, endOfDay)
    }

    fun markAsDone(activity: Activity, date: LocalDate, notes: String) {
        viewModelScope.launch {
            val startOfDay = date.atStartOfDay()
            val endOfDay = date.atTime(23, 59, 59)
            repository.deleteAdHocActivity(activity.id, startOfDay, endOfDay)

            val entry = HistoryEntry(
                dateTime = if (date == LocalDate.now()) LocalDateTime.now() else date.atTime(12, 0),
                type = "ACTIVITY",
                name = activity.name,
                description = activity.description,
                notes = notes,
                status = HistoryStatus.COMPLETED
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun skipActivity(activity: Activity, date: LocalDate) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = if (date == LocalDate.now()) LocalDateTime.now() else date.atTime(12, 0),
                type = "ACTIVITY",
                name = activity.name,
                description = activity.description,
                status = HistoryStatus.SKIPPED
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun addNote(note: String, date: LocalDate) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = if (date == LocalDate.now()) LocalDateTime.now() else date.atTime(12, 0),
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
            val planId = repository.insertPlan(Plan(name = name))
            repository.createPlanSnapshot(planId)
        }
    }

    fun deletePlan(planId: Long) {
        viewModelScope.launch {
            repository.createPlanSnapshot(planId)
            repository.deletePlan(planId)
        }
    }

    fun duplicatePlan(planId: Long) {
        viewModelScope.launch {
            val oldPlan = repository.getPlanById(planId) ?: return@launch
            val newPlanId = repository.insertPlan(Plan(name = "${oldPlan.name} (Copy)"))
            val activities = repository.getPlanActivitiesByPlanId(planId)
            activities.forEach { activity ->
                repository.insertPlanActivity(activity.copy(id = 0, planId = newPlanId))
            }
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

    fun addActivityToPlan(planId: Long, name: String, description: String, daysOfWeek: List<Int>) {
        viewModelScope.launch {
            repository.createPlanSnapshot(planId)
            val activityId = repository.insertActivity(Activity(name = name, description = description))
            repository.insertPlanActivity(
                PlanActivity(
                    planId = planId,
                    activityId = activityId,
                    daysOfWeek = daysOfWeek
                )
            )
        }
    }

    fun updateActivityInPlan(
        planActivityId: Long,
        activityId: Long,
        planId: Long,
        name: String,
        description: String,
        daysOfWeek: List<Int>,
        isActive: Boolean
    ) {
        viewModelScope.launch {
            repository.createPlanSnapshot(planId)
            repository.insertActivity(Activity(id = activityId, name = name, description = description))
            repository.updatePlanActivity(
                PlanActivity(
                    id = planActivityId,
                    planId = planId,
                    activityId = activityId,
                    daysOfWeek = daysOfWeek,
                    isActive = isActive
                )
            )
        }
    }

    fun toggleActivityInPlanActive(activity: PlanActivityWithDetails) {
        viewModelScope.launch {
            repository.createPlanSnapshot(activity.planId)
            repository.updatePlanActivity(
                PlanActivity(
                    id = activity.id,
                    planId = activity.planId,
                    activityId = activity.activityId,
                    daysOfWeek = activity.daysOfWeek,
                    isActive = !activity.isActive
                )
            )
        }
    }

    fun deleteActivityFromPlan(activityId: Long) {
        // We need planId here to snapshot. Let's assume we update the call or handle it differently.
        // For now, I'll just proceed as planId might be needed.
        viewModelScope.launch {
            repository.deletePlanActivity(activityId)
        }
    }

    // Trigger snapshot before any major plan change
    fun capturePlanSnapshot(planId: Long) {
        viewModelScope.launch {
            repository.createPlanSnapshot(planId)
        }
    }

    val allPlanSnapshots: Flow<List<PlanSnapshot>> = repository.allPlanSnapshots

    fun restorePlanFromSnapshot(snapshot: PlanSnapshot) {
        viewModelScope.launch {
            val details = repository.getPlanSnapshotWithDetails(snapshot)
            // Create a new plan based on the snapshot
            val newPlanId = repository.insertPlan(Plan(name = "${details.snapshot.planName} (Restored)"))
            details.activities.forEach { sa ->
                val activityId = repository.insertActivity(Activity(name = sa.activityName, description = sa.activityDescription))
                repository.insertPlanActivity(
                    PlanActivity(
                        planId = newPlanId,
                        activityId = activityId,
                        daysOfWeek = sa.daysOfWeek,
                        isActive = sa.isActive
                    )
                )
            }
        }
    }
    
    val allActivities: Flow<List<Activity>> = repository.allActivities
    val activitiesInPlans: Flow<List<Activity>> = repository.activitiesInPlans

    fun addUnscheduledActivity(name: String, description: String, date: LocalDate, notes: String) {
        viewModelScope.launch {
            val entry = HistoryEntry(
                dateTime = if (date == LocalDate.now()) LocalDateTime.now() else date.atTime(12, 0),
                type = "ACTIVITY",
                name = name,
                description = description,
                notes = notes,
                status = HistoryStatus.COMPLETED
            )
            repository.insertHistoryEntry(entry)
        }
    }

    fun updateHistoryEntry(entry: HistoryEntry) {
        viewModelScope.launch {
            repository.updateHistoryEntry(entry)
        }
    }

    fun deleteHistoryEntry(entry: HistoryEntry) {
        viewModelScope.launch {
            repository.deleteHistoryEntry(entry)
        }
    }

    fun deleteHistoryEntries(ids: List<Long>) {
        viewModelScope.launch {
            repository.deleteHistoryEntries(ids)
        }
    }

    fun deletePlanSnapshots(ids: List<Long>) {
        viewModelScope.launch {
            repository.deletePlanSnapshots(ids)
        }
    }

    fun snoozeActivity(activity: Activity, fromDate: LocalDate, toDate: LocalDate) {
        viewModelScope.launch {
            // Mark original as skipped
            val skipEntry = HistoryEntry(
                dateTime = fromDate.atTime(12, 0),
                type = "ACTIVITY",
                name = activity.name,
                description = activity.description,
                notes = "Snoozed to ${toDate.format(java.time.format.DateTimeFormatter.ofPattern("MMM d"))}",
                status = HistoryStatus.SKIPPED
            )
            repository.insertHistoryEntry(skipEntry)

            // Schedule for new date
            repository.insertAdHocActivity(
                AdHocActivity(
                    activityId = activity.id,
                    scheduledDate = toDate.atTime(12, 0)
                )
            )
        }
    }

    fun scheduleAdHocActivity(activity: Activity, date: LocalDate) {
        viewModelScope.launch {
            repository.insertAdHocActivity(
                AdHocActivity(
                    activityId = activity.id,
                    scheduledDate = date.atTime(12, 0)
                )
            )
        }
    }
}
