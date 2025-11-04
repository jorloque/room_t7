package com.lopez_jorge.room_t7.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name ="name" ) var name: String,
    @ColumnInfo(name ="isDone" ) var isDone: Boolean = false
)
