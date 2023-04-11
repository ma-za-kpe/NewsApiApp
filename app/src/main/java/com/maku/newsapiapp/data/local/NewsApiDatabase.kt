package com.maku.newsapiapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maku.newsapiapp.data.local.doa.ArticleDao
import com.maku.newsapiapp.data.local.model.CacheArticle

@Database(
    entities = [
        CacheArticle::class
    ],
    version = 1
)
abstract class NewsApiDatabase : RoomDatabase() {
  abstract fun articleDao(): ArticleDao
}
