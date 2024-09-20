package com.example.pushwush

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Extract notification title and body if present
        val title = remoteMessage.notification?.title ?: "Notification"
        val body = remoteMessage.notification?.body ?: "You have a new message!"
        Log.i("NOTIFICATION_DATA", "Notification - Title: $title, Body: $body")

        // Retrieve and log data payload if present
        val data = remoteMessage.data
        if (data.isNotEmpty()) {
            Log.i("DATA_PAYLOAD", "Data Payload: $data")

            // Example for logging specific keys in data payload
            val key1 = data["key1"] ?: "No Value for key1"
            val key2 = data["key2"] ?: "No Value for key2"
            Log.i("DATA_PAYLOAD", "Key1: $key1, Key2: $key2")
        }

        // Check if the app is in foreground or background and log the state
        if (!NotificationUtils.isAppIsInBackground(applicationContext)) {
            Log.i("APP_STATE", "App is in foreground")
            sendNotification(title, body) // Display notification if needed
        } else {
            Log.i("APP_STATE", "App is in background")
            sendNotification(title, body)
        }
    }

    private fun sendNotification(title: String, messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "1234"
        val defaultSoundUri: Uri = Uri.parse("android.resource://${packageName}/${R.raw.ring}")

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Handle Notification Channels for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel Human-Readable Title",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel description"
                setSound(defaultSoundUri, null)
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}
