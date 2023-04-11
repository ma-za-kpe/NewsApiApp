package com.maku.newsapiapp.core.data.api.model.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.maku.newsapiapp.core.data.api.model.Article
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.core.utils.DateTimeUtils
import javax.inject.Inject

class ApiArticleMapper @Inject constructor() : ApiMapper<Article?, DomainArticle> {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun mapToDomain(apiEntity: Article?): DomainArticle {
        return DomainArticle(
            id = 0L,
            author = apiEntity?.author.orEmpty(),
            content = apiEntity?.content.orEmpty(),
            description = apiEntity?.description.orEmpty(),
            publishedAt = DateTimeUtils.parse(apiEntity?.publishedAt.orEmpty()),
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
