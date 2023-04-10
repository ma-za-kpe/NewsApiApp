package com.maku.newsapiapp.newsbycategory.domain

import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.core.domain.repo.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocalNews @Inject constructor(private val newsRepository: NewsRepository) {
  operator fun invoke(): Flow<List<DomainArticle>> =
    newsRepository.getArticles()
}
