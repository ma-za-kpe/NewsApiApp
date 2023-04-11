package com.maku.newsapiapp.data.di

import android.content.Context
import androidx.room.Room
import com.maku.newsapiapp.data.local.NewsApiDatabase
import com.maku.newsapiapp.data.local.doa.ArticleDao
import com.maku.newsapiapp.data.local.repo.NewsCache
import com.maku.newsapiapp.data.local.repo.NewsCacheImpl
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
