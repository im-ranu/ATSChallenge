package com.guysapp.ats.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import kotlin.reflect.KClass

internal inline fun <reified T: Activity> Context.goToActivity(activity: KClass<T>) {
    val intent = Intent(this, activity.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Context.openActivity(vararg params: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(*params)
    this.startActivity(intent)
}

fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
        }
    }
    return this
}

