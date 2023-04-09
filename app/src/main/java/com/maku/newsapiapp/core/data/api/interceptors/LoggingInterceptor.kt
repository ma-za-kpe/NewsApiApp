package com.maku.newsapiapp.core.data.api.interceptors

import android.util.Log
import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
  override fun log(message: String) {
    Log.d("TAG", "log: $message")
  }
}
