package com.example.cuidartrite.view

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.cuidartrite.MainActivity
import com.example.cuidartrite.R
import java.util.Calendar

class ExerciseNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ExerciseNotification", "Alarm received!")
        showNotification(context)
        rescheduleNextAlarm(context)
    }

    private fun showNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "exercise_reminders"
        val channelName = "Lembretes de Exercícios"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificações para lembretes diários de exercícios"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Hora dos Exercícios!")
            .setContentText("Não se esqueça de fazer seus exercícios diários para cuidar da sua artrite.")
            .setSmallIcon(R.drawable.ic_check)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }

    private fun rescheduleNextAlarm(context: Context) {
        val sharedPreferences = context.getSharedPreferences("AgendaPrefs", Context.MODE_PRIVATE)
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", false)

        if (!notificationsEnabled) {
            Log.d("ExerciseNotification", "Notifications disabled, not rescheduling")
            return
        }

        val hour24 = sharedPreferences.getInt("notification_hour", 7)
        val minute = sharedPreferences.getInt("notification_minute", 0)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour24)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            add(Calendar.DAY_OF_YEAR, 1)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ExerciseNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmClockInfo = AlarmManager.AlarmClockInfo(
                    calendar.timeInMillis,
                    pendingIntent
                )
                alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
                Log.d("ExerciseNotification", "Next alarm scheduled using setAlarmClock for tomorrow at $hour24:$minute")
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d("ExerciseNotification", "Next alarm scheduled using setExactAndAllowWhileIdle for tomorrow at $hour24:$minute")
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d("ExerciseNotification", "Next alarm scheduled using setExact for tomorrow at $hour24:$minute")
            }
        } catch (e: SecurityException) {
            Log.e("ExerciseNotification", "Failed to reschedule alarm", e)
        }
    }
}

