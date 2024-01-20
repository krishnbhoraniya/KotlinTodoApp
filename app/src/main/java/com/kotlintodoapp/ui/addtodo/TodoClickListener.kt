package com.kotlintodoapp.ui.addtodo

import com.kotlintodoapp.database.entities.Todo

interface TodoClickListener {
    fun onItemClick(todo: Todo)
}