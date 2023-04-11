package com.maku.newsapiapp.core.domain.model

interface DomainMapper<D, E> {
  fun fromDomain(domainModel: D): E
  fun toDomain(): DomainArticle
}
