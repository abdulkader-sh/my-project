package com.hisnaladhkar.app.di

import com.hisnaladhkar.app.data.repository.DhikrRepositoryImpl
import com.hisnaladhkar.app.data.repository.PrayerTimesRepositoryImpl
import com.hisnaladhkar.app.data.repository.TasbihRepositoryImpl
import com.hisnaladhkar.app.domain.repository.DhikrRepository
import com.hisnaladhkar.app.domain.repository.PrayerTimesRepository
import com.hisnaladhkar.app.domain.repository.TasbihRepository
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

    @Binds
    @Singleton
    abstract fun bindTasbihRepository(impl: TasbihRepositoryImpl): TasbihRepository

    @Binds
    @Singleton
    abstract fun bindPrayerTimesRepository(impl: PrayerTimesRepositoryImpl): PrayerTimesRepository
}
