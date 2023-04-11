package com.maku.newsapiapp.domain.usecase

import com.maku.newsapiapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNetworkNews @Inject constructor(
  private val newsRepository: NewsRepository
) {
  suspend operator fun invoke(category: String) {
    val articles = newsRepository.getNewsFromNetwork(category)
    // do caching here
    newsRepository.storeArticles(articles, category)
  }
}