package com.fitflow.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitflow.data.Activity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek

@Composable
fun ScheduleScreen(viewModel: WorkoutViewModel = viewModel()) {
    val today = LocalDate.now()
    val startOfWeek = today.with(DayOfWeek.MONDAY)
    val pagerState = rememberPagerState(pageCount = { 7 }, initialPage = today.dayOfWeek.value - 1)
    
    val scope = rememberCoroutineScope()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val date = startOfWeek.plusDays(page.toLong())
                DaySchedule(date, viewModel)
            }
        }
    }

    if (showAddDialog) {
        AddActivityOrNoteDialog(
            onDismiss = { showAddDialog = false },
            onAddNote = { note -> 
                viewModel.addNote(note)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun DaySchedule(date: LocalDate, viewModel: WorkoutViewModel) {
    val activities by viewModel.getScheduledActivitiesForDay(date.dayOfWeek.value).collectAsState(initial = emptyList())
    
    var selectedActivity by remember { mutableStateOf<Activity?>(null) }
    var showOptionsDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date.format(DateTimeFormatter.ofPattern("EEEE, MMM d")),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(activities) { activity ->
                ActivityItem(activity.name) {
                    selectedActivity = activity
                    showOptionsDialog = true
                }
            }
        }
    }

    if (showOptionsDialog && selectedActivity != null) {
        ActivityOptionsDialog(
            activity = selectedActivity!!,
            onDismiss = { showOptionsDialog = false },
            onDone = { notes ->
                viewModel.markAsDone(selectedActivity!!, notes)
                showOptionsDialog = false
            },
            onSkip = {
                viewModel.skipActivity(selectedActivity!!)
                showOptionsDialog = false
            },
            onSnooze = { /* Handle snooze */
                showOptionsDialog = false
            }
        )
    }
}

@Composable
fun ActivityItem(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ActivityOptionsDialog(
    activity: Activity,
    onDismiss: () -> Unit,
    onDone: (String) -> Unit,
    onSkip: () -> Unit,
    onSnooze: () -> Unit
) {
    var showNoteInput by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf("") }

    if (!showNoteInput) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(activity.name) },
            text = { Text("What would you like to do?") },
            confirmButton = {
                TextButton(onClick = { showNoteInput = true }) { Text("Mark as Done") }
            },
            dismissButton = {
                Row {
                    TextButton(onClick = onSkip) { Text("Skip") }
                    TextButton(onClick = onSnooze) { Text("Snooze") }
                }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add a note") },
            text = {
                TextField(value = notes, onValueChange = { notes = it }, placeholder = { Text("Note (optional)") })
            },
            confirmButton = {
                TextButton(onClick = { onDone(notes) }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { showNoteInput = false }) { Text("Back") }
            }
        )
    }
}

@Composable
fun AddActivityOrNoteDialog(onDismiss: () -> Unit, onAddNote: (String) -> Unit) {
    var showNoteInput by remember { mutableStateOf(false) }
    var noteText by remember { mutableStateOf("") }

    if (!showNoteInput) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add Item") },
            text = {
                Column {
                    Button(onClick = { /* Add unscheduled activity */ }, modifier = Modifier.fillMaxWidth()) {
                        Text("Add Unscheduled Activity")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { /* Add from plan */ }, modifier = Modifier.fillMaxWidth()) {
                        Text("Add from Plan")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { showNoteInput = true }, modifier = Modifier.fillMaxWidth()) {
                        Text("Add Note")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add Note") },
            text = {
                TextField(value = noteText, onValueChange = { noteText = it }, placeholder = { Text("Enter your note here...") })
            },
            confirmButton = {
                TextButton(onClick = { onAddNote(noteText) }) { Text("Add") }
            },
            dismissButton = {
                TextButton(onClick = { showNoteInput = false }) { Text("Back") }
            }
        )
    }
}
