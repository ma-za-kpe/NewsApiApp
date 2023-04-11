package com.maku.newsapiapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.maku.newsapiapp.core.data.cache.NewsApiDatabase
import com.maku.newsapiapp.core.data.cache.doa.ArticleDao
import com.maku.newsapiapp.core.data.cache.repo.NewsCache
import com.maku.newsapiapp.core.data.cache.repo.NewsCacheImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsApiCacheModule {

  @Binds
  abstract fun bindCache(cache: NewsCacheImpl): NewsCache

  companion object {

      @Provides
      @Singleton
      fun provideDatabase(
          @ApplicationContext context: Context
      ): NewsApiDatabase {
          return Room.databaseBuilder(
              context,
              NewsApiDatabase::class.java,
              "news.db"
          ).build()
      }

      @Provides
      fun provideArticlesDao(
          newsApiDatabase: NewsApiDatabase
      ): ArticleDao = newsApiDatabase.articleDao()
  }
}
