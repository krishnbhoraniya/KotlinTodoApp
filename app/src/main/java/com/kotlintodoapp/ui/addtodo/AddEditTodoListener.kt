package com.kotlintodoapp.ui.addtodo

interface AddEditTodoListener {

    fun onSaveClick(isOldTodo: Boolean)

    fun onDeleteClick()

    fun onBackClick()
}