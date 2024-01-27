package com.kotlintodoapp.utilities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCaller
import java.io.Serializable
import java.util.*

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun getCurrentDateTime(): String {
    return dateTimeFormatter.format(Date())
}

fun getActivityLauncher(caller: ActivityResultCaller): BetterActivityResult<Intent, ActivityResult> {
    return BetterActivityResult.registerActivityForResult(caller)
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
        key,
        T::class.java
    )

    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}

infix fun <T> Boolean.ifTrue(trueValue: T): T? = if (this) trueValue else null

