package com.example.data.api

import com.example.data.model.NewsList
import com.example.data.model.Resource
import com.example.data.utils.safeApiCall
import retrofit2.Call
import retrofit2.awaitResponse
import retrofit2.http.GET
import java.io.IOException
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val webService: NewsApiWebService
) {
    suspend fun getAllNews(): Resource<NewsList>{
        return safeApiCall(
            call = { fetchNews(webService) },
            "Unable to Fetch News"
        )
    }
}

private suspend fun fetchNews(webService: NewsApiWebService): Resource<NewsList> {
    val response =  webService.getNews().awaitResponse()
    if (response.isSuccessful)
        return Resource.Success(response.body()!!)

    val errorMessage = response.errorBody()?.string()
    return Resource.Failure(IOException(errorMessage))
}

interface NewsApiWebService {
    @GET("top-headlines?country=us")
    suspend fun getNews(): Call<NewsList>
}