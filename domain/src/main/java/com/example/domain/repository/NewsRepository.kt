package com.example.domain.repository

import com.example.domain.model.NewsDomain
import com.example.domain.model.Resource


interface NewsRepository {
    suspend fun getNews(): Resource<List<NewsDomain>>
}