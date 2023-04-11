package com.maku.newsapiapp.core.data.cache.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.maku.newsapiapp.core.domain.model.DomainArticle
@Entity(tableName = "news_articles", indices = [Index(value = ["title"], unique = true)])
data class CacheArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val sourceId: String? = null,
    val sourceName: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    var category: String // We need to set this while caching in the db, this will help us avoid duplication of tables
) {
        companion object {
        fun fromDomain(domainModel: DomainArticle): CacheArticle {
            val source = domainModel.source

            return CacheArticle(
                id = domainModel.id,
                author =  domainModel?.author.orEmpty(),
                content =  domainModel?.content.orEmpty(),
                description =  domainModel?.description.orEmpty(),
                publishedAt =  domainModel?.publishedAt.orEmpty(),
                sourceId = source.id.orEmpty(),
                sourceName = source.name.orEmpty(),
                title =  domainModel?.title.orEmpty(),
                url =  domainModel?.url.orEmpty(),
                urlToImage =  domainModel?.urlToImage.orEmpty(),
                category = domainModel?.category.orEmpty()
            )
        }
    }

    fun toDomain(): DomainArticle {
        return DomainArticle(
            id,
            author.toString(),
            content.toString(),
            description.toString(),
            publishedAt.toString(),
            DomainArticle.Source(
                sourceId.toString(),
                sourceName.toString()
            ),
            title.toString(),
            url.toString(),
            urlToImage.toString(),
            category
        )
    }
}



