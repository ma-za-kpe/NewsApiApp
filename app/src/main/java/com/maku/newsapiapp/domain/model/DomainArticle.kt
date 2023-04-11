package com.maku.newsapiapp.domain.model

data class DomainArticle(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    var category: String
) {
    data class Source(
        val id: String,
        val name: String
    )
}