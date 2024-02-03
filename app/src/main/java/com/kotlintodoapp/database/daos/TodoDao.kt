package com.kotlintodoapp.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlintodoapp.database.entities.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todoList: List<Todo>)

    @Query("SELECT * from todo_table order by id DESC")
    suspend fun getAllTodos(): List<Todo>

    @Query("UPDATE todo_table set title = :title, note = :note where id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)

    @Delete
    suspend fun delete(todo: Todo)
}