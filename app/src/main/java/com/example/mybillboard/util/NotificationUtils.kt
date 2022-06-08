package com.example.mybillboard.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mybillboard.R

const val channelId = "billboard_hot_100"
const val channelName = "Billboard Hot 100"

fun createChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.enableLights(true)
        channel.enableVibration(true)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    } else {

    }
}

fun createNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.bb)
        .setContentTitle("Billboard Chart")
        .setContentText("Billboard Hot 100 is updated. Check it out!")
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    with(NotificationManagerCompat.from(context)) {
        notify(0, builder.build())
    }

}