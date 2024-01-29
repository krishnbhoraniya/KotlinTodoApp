package com.kotlintodoapp.listener

interface AddEditTodoListener {

    fun onSaveClick(isOldTodo: Boolean)

    fun onDeleteClick()

    fun onBackClick()
}