package com.maku.newsapiapp.newsbycategory.ui

import com.maku.newsapiapp.core.domain.model.DomainArticle

data class ArticlesViewState(
    val loading: Boolean = true,
    val shared: List<DomainArticle> = emptyList(),
)

