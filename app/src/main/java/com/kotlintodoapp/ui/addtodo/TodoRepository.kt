package com.kotlintodoapp.ui.addtodo

import androidx.lifecycle.LiveData
import com.kotlintodoapp.database.daos.TodoDao
import com.kotlintodoapp.database.entities.Todo

class TodoRepository(private val todoDao: TodoDao) {

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo);
    }

    fun getAllTodos(): LiveData<List<Todo>> {
        return todoDao.getAllTodos();
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo.id, todo.title, todo.note);
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo);
    }
}