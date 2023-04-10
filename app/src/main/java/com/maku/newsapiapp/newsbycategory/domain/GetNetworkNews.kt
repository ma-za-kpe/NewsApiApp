package com.maku.newsapiapp.newsbycategory.domain

import android.util.Log
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.core.domain.repo.NewsRepository
import com.maku.newsapiapp.core.utils.snackbar.SnackbarManager
import com.maku.newsapiapp.core.utils.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.maku.newsapiapp.newsbycategory.ui.ApiResultState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetNetworkNews @Inject constructor(
  private val newsRepository: NewsRepository) {
  suspend operator fun invoke(category: String) {
    val articles = newsRepository.getNewsFromNetwork(category)
    // do caching here
    newsRepository.storeArticles(articles, category)
//    if (category == "business") {
//      newsRepository.storeBusinessArticles(articles)
//    } else if (category == "sports") {
//      newsRepository.storeBusinessArticles(articles)
//    }
  }
}
//  return articles
//        // Intermediate operation to save the latest news in the cache
//        .onEach { news ->
//          Log.d("TAG", "articles main: empty 7")
//          newsRepository.storeBusinessArticles(news)
//        }
//  }

//try {
//  val response = api.getNewsByCategory(category)
//  val body = response.body()
//
//  if (response.isSuccessful && body != null) {
//    val articles = body.articles.map {
//      articleMapper.mapToDomain(it)
//    }
//    ApiResultState.Success(data = articles)
//  } else {
//    ApiResultState.Error(
//      code = response.code(),
//      message = response.message()
//    )
//  }
//} catch (e: HttpException) {
//  ApiResultState.Error(code = e.code(), message = e.message())
//  // throw NetworkException(exception.message ?: "Code ${exception.code()}")
//} catch (e: Throwable) {
//  ApiResultState.Exception(e)
//}

//suspend operator fun invoke(category: String) {
//  val articles = newsRepository.getNewsFromNetwork(category)
//  if (articles.isEmpty()) {
//    throw NoMoreNewsException("No fresh news, please try again later :(")
//  }
//  // do caching here
//  if (category == "business"){
//    newsRepository.storeBusinessArticles(articles)
//  }
//}
