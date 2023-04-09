package com.maku.newsapiapp.core.data.api

import com.maku.newsapiapp.core.data.api.model.News
import com.maku.newsapiapp.core.utils.ApiConstants
import com.maku.newsapiapp.core.utils.ApiConstants.HEADLINES_ENDPOINT
import com.maku.newsapiapp.core.utils.ApiParameters
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(HEADLINES_ENDPOINT)
    suspend fun getNewsByCategory(
        @Query(ApiParameters.CATEGORY) category: String,
        // TODO: hide this key
        @Query(ApiParameters.API_KEY) apiKey: String = "17e807cde35245aabcbc2920a27f6837",
    ): News
}