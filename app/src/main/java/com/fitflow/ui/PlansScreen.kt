package com.fitflow.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitflow.data.Plan

@Composable
fun PlansScreen(onPlanClick: (Long) -> Unit, viewModel: WorkoutViewModel = viewModel()) {
    val plans by viewModel.allPlans.collectAsState(initial = emptyList())
    var showCreatePlanDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreatePlanDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Create Plan")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text(text = "Workout Plans", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn {
                items(plans) { plan ->
                    PlanItem(
                        plan = plan, 
                        onToggle = { viewModel.togglePlanActive(plan) },
                        onClick = { onPlanClick(plan.id) }
                    )
                }
            }
        }
    }

    if (showCreatePlanDialog) {
        CreatePlanDialog(
            onDismiss = { showCreatePlanDialog = false },
            onCreate = { name -> 
                viewModel.createPlan(name)
                showCreatePlanDialog = false
            }
        )
    }
}

@Composable
fun PlanItem(plan: Plan, onToggle: () -> Unit, onClick: () -> Unit) {
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
            Column {
                Text(text = plan.name, style = MaterialTheme.typography.titleLarge)
            }
            Switch(
                checked = plan.isActive,
                onCheckedChange = { onToggle() }
            )
        }
    }
}

@Composable
fun CreatePlanDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var planName by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create New Plan") },
        text = {
            TextField(
                value = planName,
                onValueChange = { planName = it },
                label = { Text("Plan Name") }
            )
        },
        confirmButton = {
            TextButton(onClick = { onCreate(planName) }) { Text("Create") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
