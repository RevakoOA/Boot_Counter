package com.ostapr.bootcounter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduleNotification(context)
        }
    }

    private fun scheduleNotification(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // TODO change to TIME_15_MIN_MILLIS after debug
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TIME_1_MIN_MILLIS, TIME_1_MIN_MILLIS, pendingIntent)
    }

    companion object {
        private const val TIME_1_MIN_MILLIS = 60_000L
        private const val TIME_15_MIN_MILLIS = 15 * 60 * 1000L
    }
}
