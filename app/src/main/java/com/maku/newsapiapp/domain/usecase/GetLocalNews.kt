package com.maku.newsapiapp.domain.usecase

import com.maku.newsapiapp.domain.model.DomainArticle
import com.maku.newsapiapp.domain.repository.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocalNews @Inject constructor(private val newsRepository: NewsRepository) {
  operator fun invoke(): Flow<List<DomainArticle>> =
    newsRepository.getArticles()
}
