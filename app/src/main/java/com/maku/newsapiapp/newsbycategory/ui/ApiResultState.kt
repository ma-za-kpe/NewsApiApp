package com.maku.newsapiapp.newsbycategory.ui

import com.maku.newsapiapp.core.domain.model.DomainArticle

sealed class ApiResultState<T : Any> {
    class Success<T: Any>(val data: T) : ApiResultState<T>()
    // TODO: group these error message classes
    class Error<T: Any>(val code: Int, val message: String?) : ApiResultState<T>()
    class Exception<T: Any>(val e: Throwable) : ApiResultState<T>()
}

sealed interface ArticleUiState {
    /**
     * The feed is still loading.
     */
    object Loading : ArticleUiState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of article resources contained in this feed.
         */
        val articles: List<DomainArticle>, // TODO: replace this with presentation domain
//        val entertainment: List<DomainArticle>,
    ) : ArticleUiState
}

data class ArticlesViewState(
    val loading: Boolean = true,
    val business: List<DomainArticle> = emptyList(),
    val entertainment: List<DomainArticle> = emptyList(),
    val general: List<DomainArticle> = emptyList(),
    val health: List<DomainArticle> = emptyList(),
    val technology: List<DomainArticle> = emptyList(),
    val science: List<DomainArticle> = emptyList(),
    val sports: List<DomainArticle> = emptyList(),
)

