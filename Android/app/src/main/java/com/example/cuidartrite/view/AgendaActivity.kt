package com.example.cuidartrite.view

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cuidartrite.databinding.ActivityAgendaBinding
import java.util.Calendar

class AgendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaBinding
    private var selectedHour: Int = 7
    private var selectedMinute: Int = 0
    private var isAM: Boolean = true

    // Permission launcher for POST_NOTIFICATIONS
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            proceedWithScheduling()
        } else {
            Toast.makeText(this, "Permissão de notificação necessária para lembretes", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // Load saved preferences
        loadSavedPreferences()

        // Set initial time display
        updateTimeDisplay()

        // Set checkbox text
        binding.cbDailyExercises.text = "Lembretes diários de exercícios"
    }

    private fun loadSavedPreferences() {
        val sharedPreferences = getSharedPreferences("AgendaPrefs", Context.MODE_PRIVATE)

        // Load notification enabled state
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", false)
        binding.cbDailyExercises.isChecked = notificationsEnabled

        // Load saved time (default to 7:00 AM if not set)
        val savedHour24 = sharedPreferences.getInt("notification_hour", 7)
        val savedMinute = sharedPreferences.getInt("notification_minute", 0)

        // Convert 24-hour format to 12-hour format
        if (savedHour24 == 0) {
            selectedHour = 12
            isAM = true
        } else if (savedHour24 < 12) {
            selectedHour = savedHour24
            isAM = true
        } else if (savedHour24 == 12) {
            selectedHour = 12
            isAM = false
        } else {
            selectedHour = savedHour24 - 12
            isAM = false
        }

        selectedMinute = savedMinute
    }

    private fun setupListeners() {
        // Hour increment/decrement
        binding.btnHourUp.setOnClickListener {
            selectedHour = if (selectedHour == 12) 1 else selectedHour + 1
            updateTimeDisplay()
        }

        binding.btnHourDown.setOnClickListener {
            selectedHour = if (selectedHour == 1) 12 else selectedHour - 1
            updateTimeDisplay()
        }

        // Minute increment/decrement
        binding.btnMinuteUp.setOnClickListener {
            selectedMinute = (selectedMinute + 1) % 60
            updateTimeDisplay()
        }

        binding.btnMinuteDown.setOnClickListener {
            selectedMinute = if (selectedMinute == 0) 59 else selectedMinute - 1
            updateTimeDisplay()
        }

        // AM/PM toggle
        binding.btnAmPm.setOnClickListener {
            isAM = !isAM
            updateTimeDisplay()
        }

        // Analog clock click - could open a time picker dialog
        binding.analogClock.setOnClickListener {
            // Optional: Open time picker dialog
        }

        // Cancel button
        binding.btnCancel.setOnClickListener {
            finish()
        }

        // OK button
        binding.btnOk.setOnClickListener {
            if (binding.cbDailyExercises.isChecked) {
                checkPermissionsAndSchedule()
            } else {
                cancelNotification()
            }
        }
    }

    private fun updateTimeDisplay() {
        binding.tvHour.text = String.format("%02d", selectedHour)
        binding.tvMinute.text = String.format("%02d", selectedMinute)
        binding.btnAmPm.text = if (isAM) "AM" else "PM"
    }

    private fun checkPermissionsAndSchedule() {
        // Check notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    checkExactAlarmPermission()
                }
                else -> {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            checkExactAlarmPermission()
        }
    }

    private fun checkExactAlarmPermission() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Check exact alarm permission (Android 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                proceedWithScheduling()
            } else {
                // Request exact alarm permission
                Toast.makeText(
                    this,
                    "Por favor, permita alarmes exatos nas configurações",
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
            }
        } else {
            proceedWithScheduling()
        }
    }

    private fun proceedWithScheduling() {
        scheduleNotification()
    }

    private fun scheduleNotification() {
        // Convert to 24-hour format
        val hour24 = if (isAM) {
            if (selectedHour == 12) 0 else selectedHour
        } else {
            if (selectedHour == 12) 12 else selectedHour + 12
        }

        // Save preferences
        val sharedPreferences = getSharedPreferences("AgendaPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("notifications_enabled", true)
            putInt("notification_hour", hour24)
            putInt("notification_minute", selectedMinute)
            apply()
        }

        // Schedule the alarm
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour24)
            set(Calendar.MINUTE, selectedMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // If the time has already passed today, schedule for tomorrow
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
                android.util.Log.d("AgendaActivity", "Time already passed, scheduling for tomorrow")
            } else {
                android.util.Log.d("AgendaActivity", "Scheduling for today")
            }
        }

        android.util.Log.d("AgendaActivity", "Current time: ${System.currentTimeMillis()}")
        android.util.Log.d("AgendaActivity", "Scheduled time: ${calendar.timeInMillis}")
        android.util.Log.d("AgendaActivity", "Time difference: ${(calendar.timeInMillis - System.currentTimeMillis()) / 1000 / 60} minutes")

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ExerciseNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Cancel any existing alarm first
        alarmManager.cancel(pendingIntent)

        // Schedule exact alarm
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // Android 12+ - Use setAlarmClock for highest priority
                val alarmClockInfo = AlarmManager.AlarmClockInfo(
                    calendar.timeInMillis,
                    pendingIntent
                )
                alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
                android.util.Log.d("AgendaActivity", "Using setAlarmClock (Android 12+)")
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                android.util.Log.d("AgendaActivity", "Using setExactAndAllowWhileIdle (Android 6+)")
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                android.util.Log.d("AgendaActivity", "Using setExact")
            }
            android.util.Log.d("AgendaActivity", "Alarm scheduled successfully!")
        } catch (e: SecurityException) {
            android.util.Log.e("AgendaActivity", "SecurityException scheduling alarm", e)
            Toast.makeText(
                this,
                "Erro ao agendar alarme. Verifique as permissões.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        // Log the scheduled time for debugging
        val scheduledTime = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
            .format(calendar.time)
        android.util.Log.d("AgendaActivity", "Alarm scheduled for: $scheduledTime")

        Toast.makeText(
            this,
            "Lembrete agendado para ${String.format("%02d:%02d", selectedHour, selectedMinute)} ${if (isAM) "AM" else "PM"}",
            Toast.LENGTH_LONG
        ).show()

        finish()
    }

    private fun cancelNotification() {
        // Save preferences - disable notifications
        val sharedPreferences = getSharedPreferences("AgendaPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("notifications_enabled", false)
            apply()
        }

        // Cancel the alarm
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ExerciseNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)

        Toast.makeText(
            this,
            "Lembretes diários desativados",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}

