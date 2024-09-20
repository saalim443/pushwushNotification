package com.example.pushwush

import android.app.ActivityManager
import android.content.Context

private fun isAppInForeground(context: Context): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val appProcesses = activityManager.runningAppProcesses
    appProcesses?.forEach { appProcess ->
        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            && appProcess.processName == context.packageName) {
            return true
        }
    }
    return false
}
