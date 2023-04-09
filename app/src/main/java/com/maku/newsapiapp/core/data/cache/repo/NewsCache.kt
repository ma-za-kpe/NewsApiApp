package com.maku.newsapiapp.core.data.cache.repo

import com.maku.newsapiapp.core.data.cache.model.CachedArticle
import kotlinx.coroutines.flow.Flow

interface NewsCache {
    // business
    suspend fun storeBusinessArticles(articles: List<CachedArticle>)
    fun getBusinessArticles(): Flow<List<CachedArticle>>
}