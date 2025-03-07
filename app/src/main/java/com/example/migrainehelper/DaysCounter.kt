package com.example.migrainehelper

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DaysCounter {

    fun updateDailyCounter(context: Context): Int {
        val prefs = context.getSharedPreferences("daily_counter_prefs", Context.MODE_PRIVATE)
        val lastUpdatedDate = prefs.getString("last_updated", null)
        val counter = prefs.getInt("counter", 0)

        val today = getCurrentDate()

        return if (lastUpdatedDate == null || lastUpdatedDate != today) {
            val newCounter = counter + 1
            prefs.edit()
                .putInt("counter", newCounter)
                .putString("last_updated", today)
                .apply()
            newCounter
        } else {
            counter
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}