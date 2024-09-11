package com.example.todoapproom.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity
data class todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String? = null,
    @ColumnInfo
    var description: String? = null,
    @ColumnInfo
    var date:Date? = null, // استخدم Date مع محول النوع
    @ColumnInfo
    var isDone: Boolean
) : Serializable

