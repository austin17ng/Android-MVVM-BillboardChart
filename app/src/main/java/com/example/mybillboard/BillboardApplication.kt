package com.example.mybillboard

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.mybillboard.util.createChannel
import java.util.*

class BillboardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createChannel(this)
        schedule()
    }

    private fun schedule() {
        Log.d("xxx", "xxx schedule")
        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        val notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, 0)
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        alarm.setInexactRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY,
//            notifyPendingIntent
//        )
//        alarm.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY * 7,
//            notifyPendingIntent
//        )
        alarm.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            notifyPendingIntent
        )
    }
}