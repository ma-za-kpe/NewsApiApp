package com.maku.newsapiapp.core.data.cache.repo

import com.maku.newsapiapp.core.data.cache.model.CacheArticle
import kotlinx.coroutines.flow.Flow

interface NewsCache {

    suspend fun storeArticles(articles: List<CacheArticle>)
    fun getArticlesByCategory(category: String): Flow<List<CacheArticle>>
    fun getArticles(): Flow<List<CacheArticle>>

    // business
//    suspend fun storeBusinessArticles(articles: List<BusinessArticles>)
//    fun getBusinessArticles(): Flow<List<BusinessArticles>>
//
//    // sports
//    suspend fun storeSportsArticles(articles: List<SportsArticles>)
//    fun getSportsArticlesFromCache(): Flow<List<SportsArticles>>
}