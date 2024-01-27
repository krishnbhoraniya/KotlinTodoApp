package com.kotlintodoapp.ui.addtodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kotlintodoapp.R
import com.kotlintodoapp.database.entities.Todo
import com.kotlintodoapp.databinding.ActivityAddTodoBinding
import com.kotlintodoapp.utilities.IntentKey
import com.kotlintodoapp.utilities.getCurrentDateTime
import com.kotlintodoapp.utilities.ifTrue
import com.kotlintodoapp.utilities.serializable
import com.kotlintodoapp.utilities.toast

class AddTodoActivity : AppCompatActivity(), AddEditTodoListener {

    private lateinit var binding: ActivityAddTodoBinding
    private lateinit var todo: Todo
    private var oldTodo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.my_light_primary)

        getIntentData()
        setListener()
    }

    private fun getIntentData() {
        oldTodo = intent?.serializable<Todo>(IntentKey.CURRENT_TODO)
        binding.oldTodo = oldTodo
        binding.executePendingBindings()
    }

    private fun setListener() {
        binding.addEditTodoListener = this
    }

    override fun onSaveClick(isOldTodo: Boolean) {
        val title = binding.etTitle.text.toString()
        val note = binding.etNote.text.toString()

        if (title.isNotBlank() && note.isNotBlank()) {
            todo = Todo(isOldTodo ifTrue oldTodo?.id, title, note, getCurrentDateTime())
            val bundle = Bundle()
            bundle.putSerializable(IntentKey.TODO, todo)
            setIntentResult(bundle)
        } else {
            toast(getString(R.string.please_enter_valid_data))
        }
    }

    override fun onDeleteClick() {
        val bundle = Bundle()
        bundle.putSerializable(IntentKey.TODO, oldTodo)
        bundle.putBoolean(IntentKey.DELETE_TODO, true)
        setIntentResult(bundle)
    }

    override fun onBackClick() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun setIntentResult(bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}