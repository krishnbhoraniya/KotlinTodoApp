package com.kotlintodoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.kotlintodoapp.database.entities.Todo
import com.kotlintodoapp.databinding.ActivityMainBinding
import com.kotlintodoapp.ui.addtodo.AddTodoActivity
import com.kotlintodoapp.ui.addtodo.TodoAdapter
import com.kotlintodoapp.ui.addtodo.TodoClickListener
import com.kotlintodoapp.ui.addtodo.TodoViewModel
import com.kotlintodoapp.utilities.IntentKey
import com.kotlintodoapp.utilities.getActivityLauncher
import com.kotlintodoapp.utilities.serializable

class MainActivity : AppCompatActivity(), TodoClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter

    private val getContentOnResult = getActivityLauncher(this)
    private val updateOrDeleteTodoOnResult = getActivityLauncher(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.my_light_primary)

        initViews()
        initData()
        setEvent()
    }

    private fun initViews() {
        todoAdapter = TodoAdapter(this)
        binding.rvTodo.adapter = todoAdapter
    }

    private fun initData() {
        todoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TodoViewModel::class.java]

        todoViewModel.allTodo.observe(this) { todoList ->
            todoList?.let {
                todoAdapter.setData(todoList)
            }
        }
    }

    private fun setEvent() {
        binding.fabAddTodo.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            getContentOnResult.launch(intent) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val todo: Todo? = result.data?.serializable<Todo>(IntentKey.TODO)
                    if (todo != null) {
                        todoViewModel.insert(todo)
                    }
                }
            }
        }
    }

    override fun onItemClick(todo: Todo) {
        val intent = Intent(this@MainActivity, AddTodoActivity::class.java)
        intent.putExtra(IntentKey.CURRENT_TODO, todo)
        updateOrDeleteTodoOnResult.launch(intent) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todo: Todo? = result.data?.serializable<Todo>(IntentKey.TODO)
                val isDelete = result.data?.getBooleanExtra(
                    IntentKey.DELETE_TODO, false
                ) as Boolean

                if (todo != null) {
                    if (isDelete) {
                        todoViewModel.delete(todo)
                    } else {
                        todoViewModel.update(todo)
                    }
                }
            }
        }
    }
}