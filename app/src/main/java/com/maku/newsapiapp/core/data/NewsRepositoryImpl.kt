package com.maku.newsapiapp.core.data

import com.maku.newsapiapp.core.data.api.NewsApi
import com.maku.newsapiapp.core.data.api.model.mapper.ApiArticleMapper
import com.maku.newsapiapp.core.data.cache.model.CacheArticle
import com.maku.newsapiapp.core.data.cache.repo.NewsCache
import com.maku.newsapiapp.core.domain.exception.NetworkException
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.core.domain.repo.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val cache: NewsCache,
    private val articleMapper: ApiArticleMapper
) : NewsRepository {
    override suspend fun getNewsFromNetwork(category: String): List<DomainArticle> {
        return withContext(Dispatchers.IO) {
            try {
                api.getNewsByCategory(category).articles.map {
                    articleMapper.mapToDomain(it)
                }
            } catch (e: HttpException) {
                throw NetworkException(e.message ?: "Code ${e.code()}")
            } catch (e: Throwable) {
                throw Exception(e)
            }
        }
    }

    override suspend fun storeArticles(
        articles: List<DomainArticle>,
        category: String
    ) {
        val mutableList: MutableList<DomainArticle> = articles.toMutableList()
        mutableList.map {
            it.category = category
        }

        cache.storeArticles(
            mutableList.toList().map {
                CacheArticle.fromDomain(it)
            }
        )
    }

    override fun getArticlesByCategory(category: String): Flow<List<DomainArticle>> {
        return cache.getArticlesByCategory(category)
            .map { it ->
                it.map {
                    it.toDomain()
                }
            }
    }

    override fun getArticles(): Flow<List<DomainArticle>> {
        return cache.getArticles()
            .map { it ->
                it.map {
                    it.toDomain()
                }
            }
    }
}
