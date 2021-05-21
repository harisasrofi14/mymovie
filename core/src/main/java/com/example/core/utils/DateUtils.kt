package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

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