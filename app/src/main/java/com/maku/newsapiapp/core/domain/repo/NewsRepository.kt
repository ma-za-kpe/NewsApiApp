package com.maku.newsapiapp.core.domain.repo

import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.newsbycategory.ui.ApiResultState
import com.maku.newsapiapp.newsbycategory.ui.ArticleUiState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun storeBusinessArticles(articles: List<DomainArticle>)
    fun getArticlesFromCache(): Flow<List<DomainArticle>>
    suspend fun getNewsFromNetwork(category: String): List<DomainArticle>
}