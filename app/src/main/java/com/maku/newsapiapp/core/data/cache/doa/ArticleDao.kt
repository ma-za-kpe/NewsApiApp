package com.maku.newsapiapp.core.data.cache.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.newsapiapp.core.data.cache.model.CacheArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertArticles(articles: List<CacheArticle>)

  @Query("SELECT * FROM news_articles WHERE category = :category")
  fun getArticlesByCategory(category: String): Flow<List<CacheArticle>>

  @Query("SELECT * FROM news_articles")
  fun getArticles(): Flow<List<CacheArticle>>

  // business
//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  suspend fun insertBusinessArticles(articles: List<BusinessArticles>)
//
//  @Transaction
//  @Query("SELECT * FROM business_articles ORDER BY id DESC")
//  fun getAllBusinessArticles(): Flow<List<BusinessArticles>>
//
//  // sports
//
//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  suspend fun insertSportsArticles(articles: List<SportsArticles>)
//
//  @Transaction
//  @Query("SELECT * FROM sports_articles ORDER BY id DESC")
//  fun getAllSportsArticles(): Flow<List<SportsArticles>>
}
