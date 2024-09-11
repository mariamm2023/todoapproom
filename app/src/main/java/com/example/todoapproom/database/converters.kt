package com.example.todoapproom.database

import androidx.room.TypeConverter
import java.util.Date

class converters {
    @TypeConverter
    fun tomillysecond(date: Date?):Long{
        return date?.time?:0
    }
    @TypeConverter
    fun frommillysecond(millysecond:Long): Date {
        return Date(millysecond)
    }
}