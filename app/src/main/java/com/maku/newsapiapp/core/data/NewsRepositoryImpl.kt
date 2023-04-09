package com.maku.newsapiapp.core.data

import android.util.Log
import com.maku.newsapiapp.core.data.api.NewsApi
import com.maku.newsapiapp.core.data.api.model.mapper.ApiArticleMapper
import com.maku.newsapiapp.core.data.cache.model.CachedArticle
import com.maku.newsapiapp.core.data.cache.repo.NewsCache
import com.maku.newsapiapp.core.domain.exception.NetworkException
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.core.domain.repo.NewsRepository
import com.maku.newsapiapp.newsbycategory.ui.ArticleUiState
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
    override suspend fun storeBusinessArticles(articles: List<DomainArticle>) {
        cache.storeBusinessArticles(articles.map { CachedArticle.fromDomain(it) })
    }

    override fun getArticlesFromCache(): Flow<List<DomainArticle>> {
        return cache.getBusinessArticles()
            .map { it ->
                it.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun getNewsFromNetwork(category: String): List<DomainArticle> {
//        return flow {
//            while(true) {
//                val latestNews =  api.getNewsByCategory(category).articles.map {
//                    articleMapper.mapToDomain(it)
//                }
//                emit(latestNews) // Emits the result of the request to the flow
//            }
//        }

//        return withContext(Dispatchers.IO) {
//            Log.d("TAG", "articles main: empty 97")
//            api.getNewsByCategory(category).articles.map {
//                articleMapper.mapToDomain(it)
//            }
//        }

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
}


//val currentStockPriceAsFlow: StateFlow<UiState> = stockPriceDataSource
//    .latestStockList
//    .map { stockList ->
//        UiState.Success(stockList) as UiState
//    }
//    .onCompletion {
//        Timber.tag("Flow").d("Flow has completed.")
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = UiState.Loading,
//        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
//    )