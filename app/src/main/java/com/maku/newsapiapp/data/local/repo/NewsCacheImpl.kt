package com.maku.newsapiapp.data.local.repo

import com.maku.newsapiapp.data.local.doa.ArticleDao
import com.maku.newsapiapp.data.local.model.CacheArticle
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