package com.maku.newsapiapp.newsbycategory.domain

import com.maku.newsapiapp.core.domain.repo.NewsRepository
import javax.inject.Inject

class GetNetworkNews @Inject constructor(
  private val newsRepository: NewsRepository) {
  suspend operator fun invoke(category: String) {
    val articles = newsRepository.getNewsFromNetwork(category)
    // do caching here
    newsRepository.storeArticles(articles, category)
  }
}