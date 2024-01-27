package com.kotlintodoapp.ui.addtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlintodoapp.database.AppDatabase
import com.kotlintodoapp.database.entities.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    private val _allTodo: MutableLiveData<List<Todo>> by lazy { MutableLiveData<List<Todo>>() }
    val allTodo: LiveData<List<Todo>> = _allTodo

    init {
        val todoDao = AppDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepository(todoDao)
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            _allTodo.postValue(repository.getAllTodos())
        }
    }

    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
        loadData()
    }

    fun update(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
        loadData()
    }

    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
        loadData()
    }
}