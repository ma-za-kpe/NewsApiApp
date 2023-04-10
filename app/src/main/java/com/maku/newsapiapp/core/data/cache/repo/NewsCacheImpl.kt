package com.maku.newsapiapp.core.data.cache.repo

import com.maku.newsapiapp.core.data.cache.doa.ArticleDao
import com.maku.newsapiapp.core.data.cache.model.CacheArticle
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsCacheImpl @Inject constructor(
    private val articleDao: ArticleDao,
) : NewsCache {

    // business
//    override suspend fun storeBusinessArticles(articles: List<BusinessArticles>) {
//        articleDao.insertBusinessArticles(articles)
//    }
//
//    override fun getBusinessArticles(): Flow<List<BusinessArticles>> {
//        return articleDao.getAllBusinessArticles()
//    }
//
//    // sports
//    override suspend fun storeSportsArticles(articles: List<SportsArticles>) {
//        articleDao.insertSportsArticles(articles)
//    }
//
//    override fun getSportsArticlesFromCache(): Flow<List<SportsArticles>> {
//        return articleDao.getAllSportsArticles()
//    }
    override suspend fun storeArticles(articles: List<CacheArticle>) {
        articleDao.insertArticles(articles)
    }

    override fun getArticlesByCategory(category: String): Flow<List<CacheArticle>> {
        return articleDao.getArticlesByCategory(category)
    }

    override fun getArticles(): Flow<List<CacheArticle>> {
        return articleDao.getArticles()
    }
}