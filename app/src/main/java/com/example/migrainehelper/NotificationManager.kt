package com.example.migrainehelper

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val isFilteringEnabled = sharedPreferences.getBoolean("isFilteringEnabled", false)

        if (!isFilteringEnabled) {
            return
        }

        sbn?.let {
            val packageName = it.packageName
            val notificationTitle = it.notification.extras.getString("android.title") ?: ""
            val notificationText = it.notification.extras.getString("android.text") ?: ""

            if (packageName == "com.facebook.orca" && notificationTitle == "Someone") {
                // Allow this notification
                Log.d("NotificationListener", "Allowed notification: $notificationText")
            } else {
                // Block all other notifications
                cancelNotification(sbn.key)
                Log.d("NotificationListener", "Blocked notification from $packageName with title: $notificationTitle")
            }
        }
    }

}

