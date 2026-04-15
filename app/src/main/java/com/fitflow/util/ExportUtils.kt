package com.fitflow.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.fitflow.data.HistoryEntry
import java.io.File
import java.time.format.DateTimeFormatter

object ExportUtils {
    fun exportToCsv(context: Context, history: List<HistoryEntry>) {
        val fileName = "workout_history.csv"
        val csvHeader = "Date-time,Type,Name,Description,Notes,Status\n"
        val csvContent = history.joinToString("\n") { entry ->
            "${entry.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)},${entry.type},\"${entry.name}\",\"${entry.description}\",\"${entry.notes}\",${entry.status}"
        }
        
        val file = File(context.cacheDir, fileName)
        file.writeText(csvHeader + csvContent)
        
        shareFile(context, file, "text/csv")
    }

    fun exportToHtml(context: Context, history: List<HistoryEntry>) {
        val fileName = "workout_history.html"
        val htmlHeader = """
            <html>
            <head><style>table { border-collapse: collapse; width: 100%; } th, td { border: 1px solid black; padding: 8px; text-align: left; }</style></head>
            <body>
            <h2>Workout History</h2>
            <table>
            <tr><th>Date-time</th><th>Type</th><th>Name</th><th>Description</th><th>Notes</th><th>Status</th></tr>
        """.trimIndent()
        
        val htmlRows = history.joinToString("\n") { entry ->
            "<tr><td>${entry.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td><td>${entry.type}</td><td>${entry.name}</td><td>${entry.description}</td><td>${entry.notes}</td><td>${entry.status}</td></tr>"
        }
        
        val htmlFooter = "</table></body></html>"
        
        val file = File(context.cacheDir, fileName)
        file.writeText(htmlHeader + htmlRows + htmlFooter)
        
        shareFile(context, file, "text/html")
    }

    private fun shareFile(context: Context, file: File, mimeType: String) {
        val uri = FileProvider.getUriForFile(context, "com.fitflow.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = mimeType
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share Workout History"))
    }
}
