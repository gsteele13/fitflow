package com.fitflow.data

data class PlanSnapshotWithDetails(
    val snapshot: PlanSnapshot,
    val activities: List<PlanActivitySnapshot>
)
