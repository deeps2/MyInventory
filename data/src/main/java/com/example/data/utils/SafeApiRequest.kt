package com.example.data.utils

import com.example.data.model.Resource
import java.io.IOException


suspend fun <T : Any> safeApiCall(
    call: suspend () -> Resource<T>,
    errorMessage: String
): Resource<T> {
    return try {
        call()
    } catch (e: Exception) {
        Resource.Failure(IOException(errorMessage, e))
    }
}
