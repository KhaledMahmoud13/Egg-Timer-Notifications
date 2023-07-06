package com.khaled.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.khaled.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d("TAG", "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d("TAG", "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d("TAG", "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    override fun onNewToken(token: String?) {
        Log.d("TAG", "Refresh token: $token")

        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
        notificationManager?.sendNotification(messageBody, applicationContext)
    }
}