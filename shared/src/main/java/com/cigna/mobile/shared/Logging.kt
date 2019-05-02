package com.cigna.mobile.shared

import android.util.Log

/**
 * Log error
 * @property throwable optional, default is null
 * @property message error message string
 */
fun Any.logError(message: String, throwable: Throwable? = null) = Log.e(this.javaClass.name, message, throwable)

/**
 * Log info
 * @property message info message string
 */
fun Any.logInfo(message: String) = Log.i(this.javaClass.name, message)

/**
 * Log debug
 * @property message debug message string
 */
fun Any.logDebug(message: String) = Log.d(this.javaClass.name, message)

/**
 * Log verbose
 * @property message verbose message string
 */
fun Any.logVerbose(message: String) = Log.v(this.javaClass.name, message)