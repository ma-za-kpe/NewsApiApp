package com.maku.newsapiapp.data.remote

import com.maku.newsapiapp.data.remote.api.model.News
import com.maku.newsapiapp.core.utils.ApiConstants.HEADLINES_ENDPOINT
import com.maku.newsapiapp.core.utils.ApiParameters
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(HEADLINES_ENDPOINT)
    suspend fun getNewsByCategory(
        @Query(ApiParameters.CATEGORY) category: String,
        @Query(ApiParameters.API_KEY) apiKey: String
    ): News
}