package com.example.pushwush


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the notification and media playback
        Log.d("NotificationReceiver", "Notification received")
        // You can also trigger the notification from here if needed
    }
}
