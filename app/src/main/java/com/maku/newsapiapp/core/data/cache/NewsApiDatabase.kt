package com.maku.newsapiapp.core.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maku.newsapiapp.core.data.cache.doa.ArticleDao
import com.maku.newsapiapp.core.data.cache.model.CachedArticle

@Database(
    entities = [
      CachedArticle::class
    ],
    version = 1
)
abstract class NewsApiDatabase : RoomDatabase() {
  abstract fun articleDao(): ArticleDao
}
