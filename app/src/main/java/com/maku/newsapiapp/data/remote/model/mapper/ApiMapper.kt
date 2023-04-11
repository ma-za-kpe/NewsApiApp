package com.maku.newsapiapp.data.remote.api.model.mapper

interface ApiMapper<E, D> {
  fun mapToDomain(apiEntity: E): D
}
