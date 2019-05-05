package com.cigna.mobile.shared

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

suspend inline fun <reified T : Any> standardApiCall(
    noinline dbCall: suspend () -> T? = { null },
    noinline apiCall: suspend () -> Response<Any>?,
    noinline networkCallSuccess: suspend (T) -> Unit = { }
): IOResponse<T> {

    val cacheId = T::class.java.simpleName
    val cacheResult = DataCache.getData(cacheId)
    if (cacheResult != null) {
        T::class.java.logDebug("FOUND DATA IN CACHE")
        return IOResponse.success(cacheResult as T?)
    }

    val dbValue =  withContext(Dispatchers.IO) {
        val dbResult = dbCall.invoke()
        if (dbResult != null) {
            T::class.java.logDebug("FOUND DATA IN DATABASE")
            DataCache.addData(cacheId, dbResult)
            IOResponse.success(dbResult)
        }else{
            IOResponse.error(500, null)
        }
    }

    val apiValue = withContext(Dispatchers.IO){
        val apiResult = apiCall.invoke()
        when {
            apiResult == null -> IOResponse.error(500, null)
            apiResult.isSuccessful -> {
                DataCache.addData(cacheId, apiResult)
                networkCallSuccess.invoke(apiResult.body() as T)
                IOResponse.success(apiResult.body() as T)
            }
            else -> IOResponse.error(apiResult.code(), apiResult.errorBody())
        }
    }

    return if(dbValue.isSuccessful()) dbValue else apiValue
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