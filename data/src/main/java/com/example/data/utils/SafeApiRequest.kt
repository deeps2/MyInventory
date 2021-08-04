package com.example.data.utils

import com.example.data.model.Resource
import java.io.IOException


suspend fun <T : Any> safeApiCall(
    call: suspend () -> Resource<T>,
    errorMessage: String
): Resource<T> {
    return try {
        call()
    } catch (throwable: Throwable) {

        /*
        when (throwable) {
            is IOException -> Resource.Failure
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                Resource.Failure(code, errorResponse)
            }
            else -> {
                Resource.Failure(null, null)
            }
        }
         */

        Resource.Failure(IOException(errorMessage, throwable))
    }
}

/*
private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}
 */