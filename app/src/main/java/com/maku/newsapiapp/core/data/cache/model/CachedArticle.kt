package com.maku.newsapiapp.core.data.cache.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.maku.newsapiapp.core.domain.model.DomainArticle

@Entity(
    tableName = "news_articles",
    indices = [
        Index(
            value = ["title"],
            unique = true
        )
    ]
)
data class CacheArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceId: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    var category: String
) {
        companion object {
        fun fromDomain(domainModel: DomainArticle): CacheArticle {
            val source = domainModel.source

            return CacheArticle(
                id = domainModel.id,
                author = domainModel.author,
                content = domainModel.content,
                description = domainModel.description,
                publishedAt = domainModel.publishedAt,
                sourceId = source.id,
                sourceName = source.name,
                title = domainModel.title,
                url = domainModel.url,
                urlToImage = domainModel.urlToImage,
                category = domainModel.category
            )
        }
    }

    fun toDomain(): DomainArticle {
        return DomainArticle(
            id,
            author,
            content,
            description,
            publishedAt,
            DomainArticle.Source(
                sourceId,
                sourceName
            ),
            title,
            url,
            urlToImage,
            category
        )
    }
}



