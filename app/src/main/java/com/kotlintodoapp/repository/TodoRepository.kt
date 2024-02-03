package com.kotlintodoapp.repository

import com.kotlintodoapp.database.daos.TodoDao
import com.kotlintodoapp.database.entities.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun insertAll(todoList: List<Todo>) {
        todoDao.insertAll(todoList)
    }

    suspend fun getAllTodos(): List<Todo> {
        return todoDao.getAllTodos()
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo.id, todo.title, todo.note)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}