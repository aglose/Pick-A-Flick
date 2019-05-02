package com.cigna.mobile.shared

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

suspend inline fun <reified T : Any> standardApiCall(
    noinline dbCall: suspend () -> T? = { null },
    noinline apiCall: () -> Response<T>,
    noinline networkCallSuccess: suspend (T) -> Unit = { }
): IOResponse<T> {

    val cacheId = T::class.java.simpleName
    val cacheResult = com.cigna.mobile.db.DataCache.getData(cacheId)
    if (cacheResult != null) {
        return IOResponse.success(cacheResult as T?)
    }

    return withContext(Dispatchers.IO) {
        val dbResult = dbCall.invoke()
        if (dbResult != null) {
            com.cigna.mobile.db.DataCache.addData(cacheId, dbResult)
            IOResponse.success(dbResult)
        }

        val apiResult = apiCall.invoke()
        if (apiResult.isSuccessful) {
            val apiBody = apiResult.body()!!
            com.cigna.mobile.db.DataCache.addData(cacheId, apiBody)
            networkCallSuccess.invoke(apiBody)
            IOResponse.success(apiBody)
        } else {
            IOResponse.error(apiResult.code(), apiResult.errorBody())
        }
    }
}

suspend fun <T : Any> apiCallNoCache(
    dbCall: suspend () -> T? = { null },
    apiCall: suspend () -> Response<T>,
    networkCallSuccess: suspend (T) -> Unit = { }
): IOResponse<T> = withContext(Dispatchers.IO) {
    val dbResult = dbCall.invoke()
    if (dbResult != null) {
        IOResponse.success(dbResult)
    }

    val apiResult = apiCall.invoke()
    if (apiResult.isSuccessful) {
        val apiBody = apiResult.body()!!
        networkCallSuccess.invoke(apiBody)
        IOResponse.success(apiBody)
    } else {
        IOResponse.error(apiResult.code(), apiResult.errorBody())
    }
}

suspend fun <T : Any> apiCallOnly(
    apiCall: suspend () -> Response<T>,
    networkCallSuccess: suspend (T) -> Unit = { }
): IOResponse<T> = withContext(Dispatchers.IO) {
    val apiResult = apiCall.invoke()
    if (apiResult.isSuccessful) {
        val apiBody = apiResult.body()!!
        networkCallSuccess.invoke(apiBody)
        IOResponse.success(apiBody)
    } else {
        IOResponse.error(apiResult.code(), apiResult.errorBody())
    }
}

fun getLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }