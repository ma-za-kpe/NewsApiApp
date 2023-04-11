package com.maku.newsapiapp.domain.usecase

import com.maku.newsapiapp.domain.model.DomainArticle
import com.maku.newsapiapp.domain.repository.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocalNewsByCategory @Inject constructor(private val newsRepository: NewsRepository) {
  operator fun invoke(category: String): Flow<List<DomainArticle>> =
    newsRepository.getArticlesByCategory(category)
}
