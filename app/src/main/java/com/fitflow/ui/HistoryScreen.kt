package com.fitflow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitflow.data.HistoryEntry
import com.fitflow.data.HistoryStatus
import com.fitflow.util.ExportUtils
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(viewModel: WorkoutViewModel = viewModel()) {
    val history by viewModel.allHistory.collectAsState(initial = emptyList())
    var showExportDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showExportDialog = true }) {
                Icon(Icons.Default.Share, contentDescription = "Export History")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text(text = "History", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn {
                items(history) { entry ->
                    HistoryItem(entry)
                }
            }
        }
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

@Composable
fun HistoryItem(entry: HistoryEntry) {
    val backgroundColor = when (entry.status) {
        HistoryStatus.COMPLETED -> Color.Green.copy(alpha = 0.2f)
        HistoryStatus.SKIPPED -> Color.Yellow.copy(alpha = 0.2f)
        HistoryStatus.UNCOMPLETED -> Color.Red.copy(alpha = 0.2f)
        HistoryStatus.NOTE -> Color.Gray.copy(alpha = 0.2f)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier
            .background(backgroundColor)
            .padding(16.dp)
            .fillMaxWidth()
        ) {
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
