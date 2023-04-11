package com.maku.newsapiapp.ui.di

import com.maku.newsapiapp.data.repository.NewsRepositoryImpl
import com.maku.newsapiapp.domain.repository.NewsRepository
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
