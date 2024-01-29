package com.kotlintodoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlintodoapp.database.entities.Todo
import com.kotlintodoapp.databinding.ItemTodoBinding
import com.kotlintodoapp.listener.TodoClickListener

class TodoAdapter(private val todoClickListener: TodoClickListener) :
    ListAdapter<Todo, TodoAdapter.ViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemTodoBinding, todoClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }

    class ViewHolder(
        private val itemTodoBinding: ItemTodoBinding,
        private val todoClickListener: TodoClickListener
    ) :
        RecyclerView.ViewHolder(itemTodoBinding.root) {
        fun bind(todo: Todo) {
            itemTodoBinding.todoClickListener = todoClickListener
            itemTodoBinding.todo = todo
            itemTodoBinding.executePendingBindings()
        }
    }
}