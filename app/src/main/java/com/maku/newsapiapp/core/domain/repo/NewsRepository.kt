package com.maku.newsapiapp.core.domain.repo

import com.maku.newsapiapp.core.domain.model.DomainArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsFromNetwork(category: String): List<DomainArticle>

    suspend fun storeArticles(articles: List<DomainArticle>, category: String)
    fun getArticlesByCategory(category: String): Flow<List<DomainArticle>>

    fun getArticles(): Flow<List<DomainArticle>>

}