package com.fulltime.financaskotlin.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConverterDate {
    @TypeConverter
    fun toLong(date: Date?): Long = date?.time ?: Calendar.getInstance().timeInMillis

    @TypeConverter
    fun toDate(value: Long?): Date {
        val calendar = Calendar.getInstance()
        if (value != null) {
            calendar.timeInMillis = value
        }
        return calendar.time
    }
}
