package com.example.mymovie.core.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateUtils {

    companion object {

        fun fromMinutesToHHmm(minutes: Int): String {
            val hours = TimeUnit.MINUTES.toHours(minutes.toLong())
            val remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)
            return String.format("%02d h:%02d min", hours, remainMinutes)
        }

        fun dateFormat(dateInput: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateInput)
            return try {
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                if (date != null) {
                    return sdf.format(date)
                } else {
                    dateInput
                }
            } catch (e: Exception) {
                dateInput
            }

        }

    }

}