# Agenda Feature - Daily Exercise Reminders

## Overview
The Agenda screen allows users to schedule daily notifications for exercise reminders. This feature helps users maintain a consistent exercise routine to manage their arthritis.

## Features
- ✅ Time picker with hour/minute controls
- ✅ AM/PM toggle
- ✅ Visual analog clock display
- ✅ Daily exercise reminder checkbox
- ✅ Persistent notification scheduling
- ✅ Material Design UI matching the app theme

## Files Created
1. **AgendaActivity.kt** - Main activity handling the agenda screen logic
2. **ExerciseNotificationReceiver.kt** - BroadcastReceiver for handling scheduled notifications
3. **activity_agenda.xml** - Layout file for the agenda screen

## Files Modified
1. **AndroidManifest.xml** - Added AgendaActivity and ExerciseNotificationReceiver, plus required permissions
2. **HomeActivity.java** - Uncommented and connected the Agenda button
3. **strings.xml** - Added agenda string resource

## How to Use
1. Launch the app and log in
2. From the Home screen, tap the "Agenda" button
3. Check the "Lembretes diários de exercícios" checkbox
4. Use the up/down arrows to set your desired time
5. Toggle between AM/PM as needed
6. Tap "OK" to schedule the daily notification
7. Tap "CANCELAR" to cancel and return to the previous screen

## Permissions Required
- `SCHEDULE_EXACT_ALARM` - For scheduling exact alarm times
- `POST_NOTIFICATIONS` - For displaying notifications (Android 13+)

## Technical Details
- Uses AlarmManager for scheduling repeating daily notifications
- Stores user preferences in SharedPreferences
- Notifications are shown via NotificationCompat with a dedicated channel
- Supports Android API 24+ (minSdk = 24)

## Future Enhancements
- Add option to select specific days of the week
- Multiple reminder times
- Custom notification messages
- Snooze functionality
- Integration with exercise tracking

