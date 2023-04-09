package com.maku.newsapiapp.core.data.cache.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maku.newsapiapp.core.data.cache.model.CachedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(articles: List<CachedArticle>)

  @Transaction
  @Query("SELECT * FROM business_articles ORDER BY id DESC")
  fun getAllBusinessArticles(): Flow<List<CachedArticle>>
}
