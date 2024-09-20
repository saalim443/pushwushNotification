package com.example.pushwush

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lifecycleScope.launch(Dispatchers.IO) {
            val accessToken = AccessToken()
            val data = accessToken.getAccessToken()
            retrieveDeviceToken();
            Log.e("DeviceToken", "AccessToken is: $data")
        }


        if (!UtilsPermissionManage.arePermissionsGranted(this)) {
            UtilsPermissionManage.requestPermissions(this);
        } else {

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun retrieveDeviceToken() {
        // Retrieve the FCM device token
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("DeviceToken", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get the new FCM registration token
            val token = task.result
            Log.d("DeviceToken", "FCM Token: $token")
            // You can use the token as needed, e.g., send it to your server
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Handle the results of the permission requests
        if (UtilPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            // If permissions are granted, retrieve the device token
            retrieveDeviceToken()
        } else {
            Log.w("Permissions", "Permissions denied")
        }
    }
}
