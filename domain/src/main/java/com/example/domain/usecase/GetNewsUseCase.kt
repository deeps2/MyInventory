package com.example.domain.usecase

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun fetchNews() = newsRepository.getNews()

}