package com.fitflow.ui

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
                            onDelete = { viewModel.deleteActivityFromPlan(activity.id) }
                        )
                    }
                }
            }
        }
    }

    if (showAddActivityDialog) {
        AddActivityToPlanDialog(
            onDismiss = { showAddActivityDialog = false },
            onAdd = { name, desc, day ->
                viewModel.addActivityToPlan(planId, name, desc, day)
                showAddActivityDialog = false
            }
        )
    }
}

@Composable
fun PlanActivityItem(
    activity: PlanActivityWithDetails,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
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
                Text(
                    text = DayOfWeek.of(activity.dayOfWeek).getDisplayName(TextStyle.FULL, Locale.getDefault()),
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
fun AddActivityToPlanDialog(onDismiss: () -> Unit, onAdd: (String, String, Int) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf(1) } // Default to Monday

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Activity") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Activity Name") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Day:")
                
                // Simplified day selection
                (1..7).forEach { day ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = selectedDay == day, onClick = { selectedDay = day })
                        Text(
                            text = DayOfWeek.of(day).getDisplayName(TextStyle.FULL, Locale.getDefault()),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { if (name.isNotBlank()) onAdd(name, description, selectedDay) }) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
