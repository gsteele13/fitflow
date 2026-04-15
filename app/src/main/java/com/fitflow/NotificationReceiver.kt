package com.fitflow

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.fitflow.data.WorkoutDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val database = WorkoutDatabase.getDatabase(context)
        val today = LocalDate.now().dayOfWeek.value // 1 to 7

        CoroutineScope(Dispatchers.IO).launch {
            // This is a bit simplified, ideally we'd have a repository
            val dao = database.workoutDao()
            // We need a way to check if notifications are enabled in settings
            // For now, let's assume they are if this receiver is triggered
            
            // In a real app, we'd fetch the actual scheduled activities
            // val activities = dao.getScheduledActivitiesForDay(today).first()
            // if (activities.isNotEmpty()) {
            //     showNotification(context, "You have ${activities.size} workout activities planned for today!")
            // }
            
            showNotification(context, "Check your workout schedule for today!")
        }
    }

    private fun showNotification(context: Context, message: String) {
        val channelId = "workout_notifications"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Workout Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("FitFlow")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
