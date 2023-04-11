package com.maku.newsapiapp.data.remote.api.interceptors

import com.maku.newsapiapp.data.remote.api.internet.ConnectionManager
import com.maku.newsapiapp.domain.exception.NetworkUnavailableException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}

