package com.maku.newsapiapp.data.local.repo

import com.maku.newsapiapp.data.local.model.CacheArticle
import kotlinx.coroutines.flow.Flow

interface NewsCache {
    suspend fun storeArticles(articles: List<CacheArticle>)
    fun getArticlesByCategory(category: String): Flow<List<CacheArticle>>
    fun getArticles(): Flow<List<CacheArticle>>
}