package com.maku.newsapiapp.data.di

import com.maku.newsapiapp.data.remote.NewsApi
import com.maku.newsapiapp.data.remote.api.interceptors.LoggingInterceptor
import com.maku.newsapiapp.data.remote.api.interceptors.NetworkStatusInterceptor
import com.maku.newsapiapp.core.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideApi(builder: Retrofit.Builder): NewsApi {
    return builder
        .build()
        .create(NewsApi::class.java)
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
  }

  @Provides
  fun provideOkHttpClient(
      httpLoggingInterceptor: HttpLoggingInterceptor,
      networkStatusInterceptor: NetworkStatusInterceptor,
  ): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(networkStatusInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
  }

  @Provides
  fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor(loggingInterceptor)
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
  }
}
