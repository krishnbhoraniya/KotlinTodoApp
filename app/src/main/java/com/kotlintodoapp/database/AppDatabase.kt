package com.kotlintodoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlintodoapp.database.daos.TodoDao
import com.kotlintodoapp.database.entities.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao
}