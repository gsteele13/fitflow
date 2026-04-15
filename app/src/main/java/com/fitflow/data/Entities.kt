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
    val dayOfWeek: Int, // 1 (Monday) to 7 (Sunday)
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

data class PlanActivityWithDetails(
    val id: Long,
    val planId: Long,
    val activityId: Long,
    val name: String,
    val description: String,
    val dayOfWeek: Int,
    val isActive: Boolean
)
