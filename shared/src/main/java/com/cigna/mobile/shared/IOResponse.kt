package com.cigna.mobile.shared

import okhttp3.ResponseBody

/**
 * Created by c74241 on 3/8/18.
 */

class IOResponse<out T>{

    var status: Int

    val result: T?

    var errorCode: Int? = 0
    var stringError: String? = null

    var error: ResponseBody? = null

    private constructor(status: Int, data: T?) {
        this.status = status
        this.result = data
    }

    private constructor(status: Int, errorCode: Int?, errorMessage: ResponseBody?) {
        this.status = status
        this.result = null
        this.error = errorMessage
        this.errorCode = errorCode
    }

    companion object {
        const val SUCCESS  = 1
        const val FAILURE = 2
        const val LOADING = 3
        const val BAD_REQUEST = 4
        const val BACKGROUND_WORK_COMPLETE = 5

        @JvmStatic
        fun <T> success(data: T?): IOResponse<T> {
            logDebug("success: " + data?.toString())
            return IOResponse(SUCCESS, data)
        }

        @JvmStatic
        fun <T> error(errorCode: Int?, error: ResponseBody?): IOResponse<T> {
            return IOResponse(FAILURE, errorCode, error)
        }

        @JvmStatic fun <T> badRequest(): IOResponse<T> {
            return IOResponse(BAD_REQUEST, null)
        }
    }

    fun isSuccessful(): Boolean{
        return status == SUCCESS
    }

    override fun toString(): String {
        return "Response(status=$status, result=$result, errorCode=$errorCode, stringError=$stringError, error=$error)"
    }
}
