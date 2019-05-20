package com.cigna.mobile.shared

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.io.IOException

suspend inline fun <reified DBType : Any, reified ApiResponse : Any> fullServiceCall(
    noinline dbCall: suspend () -> DBType? = { null },
    noinline apiCall: suspend () -> Response<ApiResponse>,
    noinline networkCallSuccess: suspend (ApiResponse?) -> Unit = { }
): Result<DBType> {

    val cacheId = DBType::class.java.simpleName
    val cacheResult = DataCache.getData(cacheId)
    if (cacheResult != null) {
        DBType::class.java.logDebug("FOUND DATA IN CACHE")
        return Result.Success(cacheResult as DBType)
    }

    val dbValue = withContext(Dispatchers.IO) {
        val dbResult = dbCall.invoke()
        if (dbResult != null) {
            DBType::class.java.logDebug("FOUND DATA IN DATABASE")
            DataCache.addData(cacheId, dbResult)
            Result.Success(dbResult)
        }else{
            Result.Error(null, 500)
        }
    }

    val apiValue = withContext(Dispatchers.IO){
        try {
            val apiResult = apiCall.invoke()
            when {
                apiResult.isSuccessful -> {
                    DataCache.addData(cacheId, apiResult)
                    networkCallSuccess.invoke(apiResult.body())
                    Result.Success(apiResult.body() as DBType)
                }
                else -> Result.Error(code = apiResult.code(), errorBody = apiResult.errorBody())
            }
        }catch (e: Exception){
            Result.Error(code = 500)
        }
    }

    return when(dbValue){
        is Result.Success -> dbValue
        is Result.Error -> apiValue
    }
}

/**
 * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Result.Error] is
 * created based on the [errorMessage].
 */
suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Result.Error(IOException(errorMessage, e))
    }
}

fun getLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }