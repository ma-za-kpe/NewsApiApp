package com.maku.newsapiapp.data.remote.api.model.mapper

import com.maku.newsapiapp.data.remote.api.model.Article
import com.maku.newsapiapp.domain.model.DomainArticle
import javax.inject.Inject

class ApiArticleMapper @Inject constructor() : ApiMapper<Article?, DomainArticle> {

    override fun mapToDomain(apiEntity: Article?): DomainArticle {
        return DomainArticle(
            id = 0L,
            author = apiEntity?.author.orEmpty(),
            content = apiEntity?.content.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            publishedAt = apiEntity?.publishedAt.orEmpty(),
            source = mapToSource(apiEntity),
            title = apiEntity?.title.orEmpty(),
            url = apiEntity?.url.orEmpty(),
            urlToImage = apiEntity?.urlToImage.orEmpty(),
            category = ""
        )
    }

    private fun mapToSource(apiEntity: Article?): DomainArticle.Source {
        return DomainArticle.Source(
            id = apiEntity?.source?.id.orEmpty(),
            name = apiEntity?.source?.name.orEmpty()
        )
    }
}
