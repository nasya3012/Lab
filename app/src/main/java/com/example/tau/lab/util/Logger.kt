package com.example.tau.lab.util

import android.util.Log
import com.example.tau.lab.BuildConfig

object Logger {

    private const val LOGS_NEEDED = true


    fun v(tag: String, msg: String?) {
        if (LOGS_NEEDED && BuildConfig.DEBUG) Log.v(tag, msg)
    }

    fun d(tag: String, msg: String?) {
        if (LOGS_NEEDED && BuildConfig.DEBUG) Log.d(tag, msg)
    }

    fun i(tag: String, msg: String?) {
        if (LOGS_NEEDED && BuildConfig.DEBUG) Log.i(tag, msg)
    }

    fun w(tag: String, msg: String?) {
        if (LOGS_NEEDED && BuildConfig.DEBUG) Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }
}