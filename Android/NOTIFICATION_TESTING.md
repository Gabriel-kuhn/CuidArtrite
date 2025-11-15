# Testing Notifications - CuidArtrite Agenda

## What Was Fixed (Latest Update)

### 1. **Permission Handling** ✅
- Added runtime permission request for `POST_NOTIFICATIONS` (Android 13+)
- Added exact alarm permission check (Android 12+)
- Proper permission flow with user-friendly messages

### 2. **Alarm Scheduling** ✅
- **FIXED**: Now using `setAlarmClock()` for Android 12+ (highest priority, guaranteed to fire!)
- Uses `setExactAndAllowWhileIdle()` for Android 6-11
- Added automatic rescheduling after each notification fires
- Proper handling of SecurityException
- **REMOVED**: Test notification that was interfering with scheduling

### 3. **Preference Saving/Loading** ✅
- Loads saved time and checkbox state when screen opens
- Saves settings when user clicks OK
- Properly handles enable/disable of notifications

### 4. **Debugging** ✅
- Added extensive logging to track alarm scheduling
- Logs show exact time alarm is scheduled for
- Logs show time difference in minutes

## How to Test

### Quick Test (1-2 minutes from now)
1. Open the app and go to Agenda screen
2. Check "Lembretes diários de exercícios"
3. **Set time to 1-2 minutes from now** (e.g., if it's 3:45 PM, set to 3:47 PM)
4. Click OK
5. Grant permissions when prompted
6. **Wait 1-2 minutes** - notification should appear!

### Step 1: Build and Install
```bash
cd Android
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Step 2: Grant Permissions
1. Open the app
2. Navigate to Agenda screen
3. Check "Lembretes diários de exercícios"
4. Set a time (1-2 minutes from now for quick testing)
5. Click OK
6. **Grant notification permission** when prompted
7. **Grant exact alarm permission** in settings if prompted

### Step 3: Check Logs (IMPORTANT!)
```bash
adb logcat | grep -E "AgendaActivity|ExerciseNotification"
```

You should see:
```
AgendaActivity: Scheduling for today
AgendaActivity: Time difference: 2 minutes
AgendaActivity: Using setAlarmClock (Android 12+)
AgendaActivity: Alarm scheduled successfully!
```

### Step 4: Wait for Notification
- The alarm is scheduled for the exact time you selected
- If the time has already passed today, it schedules for tomorrow
- After the first notification fires, it automatically reschedules for the next day

### Step 5: Verify Settings Persistence
1. Close the app completely
2. Reopen and go to Agenda screen
3. **Verify**: Checkbox should still be checked
4. **Verify**: Time should be the same as you set

## Troubleshooting

### No notification appears?
1. Check notification permissions: Settings → Apps → CuidArtrite → Notifications
2. Check exact alarm permission: Settings → Apps → CuidArtrite → Alarms & reminders
3. Check battery optimization: Make sure app is not restricted
4. Check Do Not Disturb mode

### Notification appears but doesn't repeat?
- Check logcat for "ExerciseNotification" logs
- Verify the alarm is being rescheduled after each notification

### Check Logs
```bash
adb logcat | grep ExerciseNotification
```

You should see:
- "Alarm received!" when notification fires
- "Next alarm scheduled for tomorrow at HH:MM"

## Key Changes Made

### AgendaActivity.kt
- Added permission launchers and checks
- Added `checkPermissionsAndSchedule()` method
- Added `checkExactAlarmPermission()` method
- Changed to `setExactAndAllowWhileIdle()` instead of `setRepeating()`
- Added immediate test notification on OK click
- Added `loadSavedPreferences()` to restore settings

### ExerciseNotificationReceiver.kt
- Added `rescheduleNextAlarm()` method
- Added logging for debugging
- Automatically schedules next day's alarm after firing

## Android Version Compatibility

- **Android 6-11**: Works without special permissions
- **Android 12+**: Requires exact alarm permission
- **Android 13+**: Requires notification permission + exact alarm permission

All versions are properly handled in the code!

