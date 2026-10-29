package com.hisnaladhkar.app.di

import com.hisnaladhkar.app.data.repository.DhikrRepositoryImpl
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDhikrRepository(impl: DhikrRepositoryImpl): DhikrRepository
}
