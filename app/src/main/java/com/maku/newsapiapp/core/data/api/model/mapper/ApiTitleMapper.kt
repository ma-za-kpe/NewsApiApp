package com.maku.newsapiapp.core.data.api.model.mapper

import com.maku.newsapiapp.core.data.api.model.Article
import javax.inject.Inject

class ApiTitleMapper @Inject constructor() : ApiMapper<Article?, DomainArticleTitle> {
    override fun mapToDomain(apiEntity: Article?): DomainArticleTitle {
        return DomainArticleTitle(
            author = apiEntity?.author.orEmpty(),
            publishedAt = apiEntity?.publishedAt.orEmpty(),
            title = apiEntity?.title.orEmpty(),
        )
    }
}
