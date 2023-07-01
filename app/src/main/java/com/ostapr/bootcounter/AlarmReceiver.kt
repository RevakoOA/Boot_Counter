package com.ostapr.bootcounter

import android.Manifest
import android.R
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        // TODO move this to different class
        // TODO add logic to decide on the text
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle()
            .setContentText()
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(false).apply {
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent =
                    PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                setContentIntent(pendingIntent)
            }

        // Get an instance of the NotificationManagerCompat
        val notificationManager = NotificationManagerCompat.from(context)

        // Build and display the notification
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO request permissions
            return
        }
        notificationManager.notify(BOOT_NOTIFICATION_ID, builder.build())
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val BOOT_NOTIFICATION_ID = 10_001
    }

}