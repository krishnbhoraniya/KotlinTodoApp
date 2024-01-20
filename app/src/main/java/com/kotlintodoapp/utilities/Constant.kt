package com.kotlintodoapp.utilities

import android.icu.text.SimpleDateFormat
import java.util.*

/**
 * Date formats
 */
val dateTimeFormatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a", Locale.US)

class IntentKey {
    companion object {
        const val TODO = "todo"
        const val CURRENT_TODO = "current_todo"
        const val DELETE_TODO = "delete_todo"
    }
}