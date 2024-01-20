package com.kotlintodoapp.ui.addtodo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlintodoapp.database.entities.Todo
import com.kotlintodoapp.databinding.ItemTodoBinding

class TodoAdapter(private val todoClickListener: TodoClickListener) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private val todoArrayList = ArrayList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemTodoBinding, todoClickListener)
    }

    override fun getItemCount(): Int {
        return todoArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoArrayList[position]
        holder.bind(todo)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(todoList: List<Todo>) {
        todoArrayList.addAll(todoList)
        notifyDataSetChanged()
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