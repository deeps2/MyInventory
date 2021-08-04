package com.example.data.repository

import com.example.data.api.NewsRemoteDataSource
import com.example.data.model.NewsList
import com.example.data.model.Resource
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val networkDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(): Resource<NewsList> {
        return withContext(Dispatchers.IO) {
            networkDataSource.getAllNews()
        }
    }
}