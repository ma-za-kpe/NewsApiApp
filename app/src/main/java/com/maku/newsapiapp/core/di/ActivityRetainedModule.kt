package com.maku.newsapiapp.core.di

import com.maku.newsapiapp.core.data.NewsRepositoryImpl
import com.maku.newsapiapp.core.domain.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

  @Binds
  @ActivityRetainedScoped
  abstract fun bindAnimalRepository(repository: NewsRepositoryImpl): NewsRepository

}
