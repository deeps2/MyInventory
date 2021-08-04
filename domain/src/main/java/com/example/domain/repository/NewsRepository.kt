package com.example.domain.repository

import com.example.data.model.Resource
import com.example.domain.model.NewsDomain


interface NewsRepository {
    suspend fun getNews(): Resource<NewsDomain>
}