package com.example.data.api

import com.example.data.model.NewsList
import com.example.data.model.Resource
import com.example.data.utils.safeApiCall
import com.example.domain.model.NewsDomain
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import java.io.IOException
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val webService: NewsApiWebService
) {
    suspend fun getAllNews(): Resource<NewsDomain> {
        return safeApiCall(
            call = {
                fetchNews(webService)
            },
            "RETROFIT_EXCEPTION"
        )
    }
}

private suspend fun fetchNews(webService: NewsApiWebService): Resource<NewsDomain> {
    val response = webService.getNews().awaitResponse()
    if (response.isSuccessful && response.body() != null) {
        val newsList = response.body()
    }

    val errorMessage = response.errorBody()?.string()
    return Resource.Failure(IOException(errorMessage))
}

interface NewsApiWebService {
    @GET("top-headlines?country=us&category=business&apiKey=455e3b21f82f42aabfb438d4204d6ceb")
    suspend fun getNews(): Call<NewsList>
}