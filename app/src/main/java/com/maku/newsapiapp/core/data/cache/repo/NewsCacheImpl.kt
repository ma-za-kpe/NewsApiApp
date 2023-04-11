package com.maku.newsapiapp.core.data.cache.repo

import com.maku.newsapiapp.core.data.cache.doa.ArticleDao
import com.maku.newsapiapp.core.data.cache.model.CacheArticle
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsCacheImpl @Inject constructor(
    private val articleDao: ArticleDao,
) : NewsCache {
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