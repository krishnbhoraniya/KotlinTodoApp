package com.kotlintodoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlintodoapp.database.daos.TodoDao
import com.kotlintodoapp.database.entities.Todo
import kotlin.concurrent.Volatile

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "todo_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getTodoDao(): TodoDao
}