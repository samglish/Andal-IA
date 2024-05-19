package com.dev.mahamat.andal_ia.di

import android.content.Context
import androidx.room.Room
import com.dev.mahamat.andal_ia.model.local.AppDatabase
import com.dev.mahamat.andal_ia.model.local.ChatsDao
import com.dev.mahamat.andal_ia.model.repository.RepoImpl
import com.dev.mahamat.andal_ia.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(): Repository = RepoImpl()

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): ChatsDao = appDatabase.dao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "chats"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}