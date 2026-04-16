package com.fitflow.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitflow.data.PlanActivityWithDetails
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanDetailsScreen(planId: Long, onBack: () -> Unit, viewModel: WorkoutViewModel = viewModel()) {
    val activities by viewModel.getPlanActivities(planId).collectAsState(initial = emptyList())
    var showAddActivityDialog by remember { mutableStateOf(false) }
    var activityToEdit by remember { mutableStateOf<PlanActivityWithDetails?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Plan") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddActivityDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Activity")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            if (activities.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No activities in this plan. Add one!")
                }
            } else {
                LazyColumn {
                    items(activities) { activity ->
                        PlanActivityItem(
                            activity = activity,
                            onToggle = { viewModel.toggleActivityInPlanActive(activity) },
                            onDelete = { viewModel.deleteActivityFromPlan(activity.id) },
                            onClick = { activityToEdit = activity }
                        )
                    }
                }
            }
        }
    }

    if (showAddActivityDialog) {
        ActivityDialog(
            onDismiss = { showAddActivityDialog = false },
            onConfirm = { name, desc, days ->
                viewModel.addActivityToPlan(planId, name, desc, days)
                showAddActivityDialog = false
            }
        )
    }

    if (activityToEdit != null) {
        val activity = activityToEdit!!
        ActivityDialog(
            initialName = activity.name,
            initialDescription = activity.description,
            initialDays = activity.daysOfWeek,
            title = "Edit Activity",
            confirmLabel = "Save",
            onDismiss = { activityToEdit = null },
            onConfirm = { name, desc, days ->
                viewModel.updateActivityInPlan(
                    planActivityId = activity.id,
                    activityId = activity.activityId,
                    planId = activity.planId,
                    name = name,
                    description = desc,
                    daysOfWeek = days,
                    isActive = activity.isActive
                )
                activityToEdit = null
            }
        )
    }
}

@Composable
fun PlanActivityItem(
    activity: PlanActivityWithDetails,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = activity.name, style = MaterialTheme.typography.titleLarge)
                val daysString = activity.daysOfWeek.sorted().joinToString(", ") {
                    DayOfWeek.of(it).getDisplayName(TextStyle.SHORT, Locale.getDefault())
                }
                Text(
                    text = daysString,
                    style = MaterialTheme.typography.bodyMedium
                )
                if (activity.description.isNotEmpty()) {
                    Text(text = activity.description, style = MaterialTheme.typography.bodySmall)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = activity.isActive,
                    onCheckedChange = { onToggle() }
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun ActivityDialog(
    initialName: String = "",
    initialDescription: String = "",
    initialDays: List<Int> = emptyList(),
    title: String = "Add Activity",
    confirmLabel: String = "Add",
    onDismiss: () -> Unit,
    onConfirm: (String, String, List<Int>) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var description by remember { mutableStateOf(initialDescription) }
    val selectedDays = remember { mutableStateListOf<Int>().apply { addAll(initialDays) } }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Activity Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Days:")
                
                (1..7).forEach { day ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable {
                            if (selectedDays.contains(day)) selectedDays.remove(day)
                            else selectedDays.add(day)
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedDays.contains(day),
                            onCheckedChange = {
                                if (it) {
                                    if (!selectedDays.contains(day)) selectedDays.add(day)
                                } else {
                                    selectedDays.remove(day)
                                }
                            }
                        )
                        Text(
                            text = DayOfWeek.of(day).getDisplayName(TextStyle.FULL, Locale.getDefault()),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { if (name.isNotBlank() && selectedDays.isNotEmpty()) onConfirm(name, description, selectedDays.toList()) }) { Text(confirmLabel) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
