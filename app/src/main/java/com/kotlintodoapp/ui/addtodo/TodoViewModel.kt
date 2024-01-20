package com.kotlintodoapp.ui.addtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kotlintodoapp.database.AppDatabase
import com.kotlintodoapp.database.entities.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodo: LiveData<List<Todo>>

    init {
        val todoDao = AppDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepository(todoDao)
        allTodo = repository.getAllTodos()
    }

    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }
}