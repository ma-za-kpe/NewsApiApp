//package com.maku.newsapiapp.core.data.cache.model
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.maku.newsapiapp.core.domain.model.DomainArticle
//
//@Entity(tableName = "sports_articles")
//data class SportsArticles(
//    @PrimaryKey(autoGenerate = true)
//    override var id: Long,
//    override val author: String? = null,
//    override val content: String? = null,
//    override val description: String? = null,
//    override val publishedAt: String? = null,
//    override val sourceId: String? = null,
//    override val sourceName: String? = null,
//    override val title: String? = null,
//    override val url: String? = null,
//    override val urlToImage: String? = null
//) : CachedArticle(
//    id,
//    author,
//    content,
//    description,
//    publishedAt,
//    sourceId,
//    sourceName,
//    title,
//    url,
//    urlToImage
//) {
//    companion object {
//        fun fromDomain(domainModel: DomainArticle): SportsArticles {
//            val source = domainModel.source
//
//            return SportsArticles(
//                id = 0L,
//                author =  domainModel?.author.orEmpty(),
//                content =  domainModel?.content.orEmpty(),
//                description =  domainModel?.description.orEmpty(),
//                publishedAt =  domainModel?.publishedAt.orEmpty(),
//                sourceId = source.id.orEmpty(),
//                sourceName = source.name.orEmpty(),
//                title =  domainModel?.title.orEmpty(),
//                url =  domainModel?.url.orEmpty(),
//                urlToImage =  domainModel?.urlToImage.orEmpty()
//            )
//        }
//    }
//
//    fun toDomain(): DomainArticle {
//        return DomainArticle(
//            author.toString(),
//            content.toString(),
//            description.toString(),
//            publishedAt.toString(),
//            DomainArticle.Source(
//                sourceId.toString(),
//                sourceName.toString()
//            ),
//            title.toString(),
//            url.toString(),
//            urlToImage.toString()
//        )
//    }
//
//}
