package com.maku.newsapiapp.core.data.cache.repo

import com.maku.newsapiapp.core.data.cache.doa.ArticleDao
import com.maku.newsapiapp.core.data.cache.model.CachedArticle
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsCacheImpl @Inject constructor(
    private val articleDao: ArticleDao,
) : NewsCache {
    override suspend fun storeBusinessArticles(articles: List<CachedArticle>) {
        articleDao.insert(articles)
    }

    override fun getBusinessArticles(): Flow<List<CachedArticle>> {
        return articleDao.getAllBusinessArticles()
    }
}