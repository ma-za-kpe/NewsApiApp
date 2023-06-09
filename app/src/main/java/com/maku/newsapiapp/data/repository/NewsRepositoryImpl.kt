package com.maku.newsapiapp.data.repository

import com.maku.newsapiapp.BuildConfig
import com.maku.newsapiapp.data.remote.NewsApi
import com.maku.newsapiapp.data.remote.api.model.mapper.ApiArticleMapper
import com.maku.newsapiapp.data.local.model.CacheArticle
import com.maku.newsapiapp.data.local.repo.NewsCache
import com.maku.newsapiapp.domain.exception.NetworkException
import com.maku.newsapiapp.domain.model.DomainArticle
import com.maku.newsapiapp.domain.repository.NewsRepository
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
        return withContext(Dispatchers.IO) { // move dispatcher out as parameter for testing
            try {
                //TODO: find a more secure way to hide keys because API keys are still recoverable by decompiling an APK => https://github.com/google/secrets-gradle-plugin
                api.getNewsByCategory(category,BuildConfig.apiKey).articles.map {
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
