package com.example.todoapproom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.todoapproom.database.doa.tododoa
import com.example.todoapproom.database.model.todo
import java.util.Date

@Database(entities =[todo::class], version = 3 , exportSchema = false)
@TypeConverters(converters::class)
abstract class tododatabase:RoomDatabase() {
    abstract fun tododao():tododoa

    companion object{
        val databasename="todo-database"
        private var mydatabase:tododatabase?=null
        fun getinstance(context:Context):tododatabase{
            if(mydatabase==null){
                mydatabase= Room.databaseBuilder(
                    context,tododatabase::class.java,databasename
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return mydatabase!!
        }
    }
}