package com.example.todoapproom.database.doa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapproom.database.model.todo
import java.util.Date

@Dao
interface tododoa {
    @Insert
    fun addtode(todo:todo )
    @Update
    fun updatetodo(todo:todo)
    @Delete
    fun delettodo(todo:todo)
    @Query("select * from todo")
    fun getAlltodo():List<todo>
@Query("select * from todo where date=:data")
    fun gettodobudate(data:Date):MutableList<todo>

}