package com.maku.newsapiapp.core.data.api.model.mapper

interface ApiMapper<E, D> {
  fun mapToDomain(apiEntity: E): D
}
