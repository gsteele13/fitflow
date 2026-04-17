package com.fitflow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitflow.data.HistoryEntry
import com.fitflow.data.HistoryStatus
import com.fitflow.util.ExportUtils
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(viewModel: WorkoutViewModel = viewModel()) {
    val history by viewModel.allHistory.collectAsState(initial = emptyList())
    var showExportDialog by remember { mutableStateOf(false) }
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    var isSelectionMode by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var entryToEdit by remember { mutableStateOf<HistoryEntry?>(null) }

    Scaffold(
        topBar = {
            if (isSelectionMode) {
                TopAppBar(
                    title = { Text("${selectedIds.size} selected") },
                    actions = {
                        IconButton(onClick = {
                            if (selectedIds.size == history.size) {
                                selectedIds = emptySet()
                            } else {
                                selectedIds = history.map { it.id }.toSet()
                            }
                        }) {
                            Text(if (selectedIds.size == history.size) "Deselect All" else "Select All", style = MaterialTheme.typography.labelSmall)
                        }
                        IconButton(onClick = {
                            viewModel.deleteHistoryEntries(selectedIds.toList())
                            selectedIds = emptySet()
                            isSelectionMode = false
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            isSelectionMode = false
                            selectedIds = emptySet()
                        }) {
                            Text("✕")
                        }
                    }
                )
            } else {
                TopAppBar(title = { Text("History") })
            }
        },
        floatingActionButton = {
            if (!isSelectionMode) {
                FloatingActionButton(onClick = { showExportDialog = true }) {
                    Icon(Icons.Default.Share, contentDescription = "Export History")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            LazyColumn {
                items(history) { entry ->
                    HistoryItem(
                        entry = entry,
                        isSelected = selectedIds.contains(entry.id),
                        isSelectionMode = isSelectionMode,
                        onLongClick = {
                            isSelectionMode = true
                            selectedIds = selectedIds + entry.id
                        },
                        onClick = {
                            if (isSelectionMode) {
                                if (selectedIds.contains(entry.id)) {
                                    selectedIds = selectedIds - entry.id
                                    if (selectedIds.isEmpty()) isSelectionMode = false
                                } else {
                                    selectedIds = selectedIds + entry.id
                                }
                            } else {
                                entryToEdit = entry
                            }
                        }
                    )
                }
            }
        }
    }

    if (entryToEdit != null) {
        EditHistoryDialog(
            entry = entryToEdit!!,
            onDismiss = { entryToEdit = null },
            onSave = { updatedEntry ->
                viewModel.updateHistoryEntry(updatedEntry)
                entryToEdit = null
            },
            onDelete = {
                viewModel.deleteHistoryEntry(entryToEdit!!)
                entryToEdit = null
            }
        )
    }

    if (showExportDialog) {
        ExportHistoryDialog(
            onDismiss = { showExportDialog = false },
            onExport = { format, range ->
                // Filtering based on 'range' would happen here or in the ViewModel
                if (format == "CSV") {
                    ExportUtils.exportToCsv(context, history)
                } else {
                    ExportUtils.exportToHtml(context, history)
                }
                showExportDialog = false
            }
        )
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun HistoryItem(
    entry: HistoryEntry,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onLongClick: () -> Unit,
    onClick: () -> Unit
) {
    val baseBackgroundColor = when (entry.status) {
        HistoryStatus.COMPLETED -> Color.Green.copy(alpha = 0.2f)
        HistoryStatus.SKIPPED -> Color.Yellow.copy(alpha = 0.2f)
        HistoryStatus.UNCOMPLETED -> Color.Red.copy(alpha = 0.2f)
        HistoryStatus.NOTE -> Color.Gray.copy(alpha = 0.2f)
    }
    
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else baseBackgroundColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelectionMode) {
                Checkbox(checked = isSelected, onCheckedChange = { onClick() })
                Spacer(modifier = Modifier.width(8.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = entry.name, style = MaterialTheme.typography.titleMedium)
                if (entry.description.isNotEmpty()) {
                    Text(text = entry.description, style = MaterialTheme.typography.bodySmall)
                }
                if (entry.notes.isNotEmpty()) {
                    Text(text = "Notes: ${entry.notes}", style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    text = entry.dateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm")),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun EditHistoryDialog(
    entry: HistoryEntry,
    onDismiss: () -> Unit,
    onSave: (HistoryEntry) -> Unit,
    onDelete: () -> Unit
) {
    var notes by remember { mutableStateOf(entry.notes) }
    var status by remember { mutableStateOf(entry.status) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit History Entry") },
        text = {
            Column {
                Text(entry.name, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Status:")
                Row {
                    HistoryStatus.entries.forEach { s ->
                        FilterChip(
                            selected = status == s,
                            onClick = { status = s },
                            label = { Text(s.name) },
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onSave(entry.copy(notes = notes, status = status)) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onDelete, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        }
    )
}

@Composable
fun ExportHistoryDialog(onDismiss: () -> Unit, onExport: (String, String) -> Unit) {
    var exportType by remember { mutableStateOf("CSV") }
    var dateRange by remember { mutableStateOf("All Dates") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Export History") },
        text = {
            Column {
                Text("Select Format:")
                Row {
                    RadioButton(selected = exportType == "CSV", onClick = { exportType = "CSV" })
                    Text("CSV", modifier = Modifier.padding(top = 12.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(selected = exportType == "HTML", onClick = { exportType = "HTML" })
                    Text("HTML", modifier = Modifier.padding(top = 12.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Date Range:")
                val ranges = listOf("All Dates", "Last 7 Days", "Last 30 Days", "This Month")
                ranges.forEach { range ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        RadioButton(selected = dateRange == range, onClick = { dateRange = range })
                        Text(range, modifier = Modifier.padding(top = 12.dp))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onExport(exportType, dateRange) }) { Text("Export") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
