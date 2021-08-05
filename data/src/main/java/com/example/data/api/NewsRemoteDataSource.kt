package com.example.data.api

import com.example.data.model.NewsList
import com.example.data.utils.safeApiCall
import com.example.domain.model.NewsDomain
import com.example.domain.model.Resource
import retrofit2.http.GET
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val webService: NewsApiWebService
) {
    suspend fun getAllNews(): Resource<List<NewsDomain>> {
        return safeApiCall(
            call = {
                fetchNews(webService)
            },
            "RETROFIT_EXCEPTION"
        )
    }
}

private suspend fun fetchNews(webService: NewsApiWebService): Resource<List<NewsDomain>> {
    val response = webService.getNews()

    val newsDomain = response.articleList.map {
        NewsDomain(
            it.title?: "",
            it.description?: "",
            it.imageUrl ?: ""
        )
    }

    return Resource.Success(newsDomain)
}

interface NewsApiWebService {
    @GET("top-headlines?country=us&category=business&apiKey=455e3b21f82f42aabfb438d4204d6ceb")
    suspend fun getNews(): NewsList
}