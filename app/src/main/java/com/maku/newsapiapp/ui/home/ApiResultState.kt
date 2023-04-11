package com.maku.newsapiapp.ui.home

import com.maku.newsapiapp.domain.model.DomainArticle

data class ArticlesViewState(
    val loading: Boolean = true,
    val shared: List<DomainArticle> = emptyList(),
)

