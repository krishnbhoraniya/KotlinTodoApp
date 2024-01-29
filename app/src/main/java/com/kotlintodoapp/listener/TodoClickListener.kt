package com.kotlintodoapp.listener

import com.kotlintodoapp.database.entities.Todo

interface TodoClickListener {
    fun onItemClick(todo: Todo)
}