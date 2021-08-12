package com.example.presentation.util

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface StringLoader {
    fun get(@StringRes resourceId: Int, vararg formatArgs: Any): String
    fun getList(@ArrayRes resourceId: Int): List<String>
    companion object {
        operator fun invoke(context: Context): StringLoader {
            return object : StringLoader {
                override fun get(resourceId: Int, vararg formatArgs: Any): String {
                    return context.getString(resourceId, *formatArgs)
                }
                override fun getList(resourceId: Int): List<String> {
                    return context.resources.getStringArray(resourceId).toList()
                }
            }
        }
    }
}