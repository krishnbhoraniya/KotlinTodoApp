package com.kotlintodoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlintodoapp.database.entities.Todo
import com.kotlintodoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    private val _allTodo: MutableLiveData<List<Todo>> by lazy { MutableLiveData<List<Todo>>() }
    val allTodo: LiveData<List<Todo>> = _allTodo

    init {
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

    fun insertAll(todoList: List<Todo>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAll(todoList)
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