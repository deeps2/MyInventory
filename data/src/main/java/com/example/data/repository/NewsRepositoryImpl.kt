package com.example.data.repository

import com.example.data.api.NewsRemoteDataSource
import com.example.domain.model.NewsDomain
import com.example.domain.model.Resource
import com.example.domain.repository.NewsRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteSource: NewsRemoteDataSource
    //private val localDataSource
) : NewsRepository {

    override suspend fun getNews(): Resource<List<NewsDomain>> {
        //return withContext(Dispatchers.IO) {
            return newsRemoteSource.getAllNews()
        //}
    }
}