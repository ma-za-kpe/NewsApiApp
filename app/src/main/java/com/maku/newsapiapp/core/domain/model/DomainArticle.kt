package com.maku.newsapiapp.core.domain.model

import java.time.LocalDateTime

data class DomainArticle(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: LocalDateTime,
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