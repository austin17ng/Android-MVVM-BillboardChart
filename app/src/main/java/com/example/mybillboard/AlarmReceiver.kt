package com.example.mybillboard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.mybillboard.util.createNotification

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        Log.d("xxx", "alarm receive")
        createNotification(context!!)
    }
}