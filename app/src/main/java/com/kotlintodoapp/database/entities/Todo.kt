package com.kotlintodoapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val note: String?,
    val date: String
) : Serializable
