package com.fitflow.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String
)

@Entity(tableName = "plans")
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val isActive: Boolean = true
)

@Entity(tableName = "plan_activities")
data class PlanActivity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val planId: Long,
    val activityId: Long,
    val daysOfWeek: List<Int>, // e.g. [1, 3, 5]
    val isActive: Boolean = true
)

enum class HistoryStatus {
    COMPLETED, SKIPPED, UNCOMPLETED, NOTE
}

@Entity(tableName = "history")
data class HistoryEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateTime: LocalDateTime,
    val type: String, // "ACTIVITY" or "NOTE"
    val name: String,
    val description: String,
    val notes: String = "",
    val status: HistoryStatus
)

@Entity(tableName = "plan_snapshots")
data class PlanSnapshot(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val originalPlanId: Long,
    val planName: String,
    val snapshotDate: LocalDateTime
)

@Entity(tableName = "plan_activity_snapshots")
data class PlanActivitySnapshot(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val planSnapshotId: Long,
    val activityName: String,
    val activityDescription: String,
    val daysOfWeek: List<Int>,
    val isActive: Boolean
)

data class PlanActivityWithDetails(
    val id: Long,
    val planId: Long,
    val activityId: Long,
    val name: String,
    val description: String,
    val daysOfWeek: List<Int>,
    val isActive: Boolean
)
