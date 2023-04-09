package com.maku.newsapiapp.core.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maku.newsapiapp.core.domain.model.DomainArticle

@Entity(tableName = "business_articles")
class CachedArticle (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val author:  String? = null,
    val content:  String? = null,
    val description:  String? = null,
    val publishedAt:  String? = null,
    val sourceId:  String? = null,
    val sourceName:  String? = null,
    val title:  String? = null,
    val url:  String? = null,
    val urlToImage: String? = null
) {
    companion object {
        fun fromDomain(domainModel: DomainArticle): CachedArticle {
            val source = domainModel.source

            return CachedArticle(
                id = 0L,
                author =  domainModel?.author.orEmpty(),
                content =  domainModel?.content.orEmpty(),
                description =  domainModel?.description.orEmpty(),
                publishedAt =  domainModel?.publishedAt.orEmpty(),
                sourceId = source.id.orEmpty(),
                sourceName = source.name.orEmpty(),
                title =  domainModel?.title.orEmpty(),
                url =  domainModel?.url.orEmpty(),
                urlToImage =  domainModel?.urlToImage.orEmpty()
            )
        }
    }

    fun toDomain(): DomainArticle {
        return DomainArticle(
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
            urlToImage.toString()
        )
    }
}
