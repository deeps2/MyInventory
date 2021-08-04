package com.example.domain.repository

import com.example.data.model.Resource
import com.example.domain.model.News


interface NewsRepository {
    suspend fun getNews(): Resource<News>
}