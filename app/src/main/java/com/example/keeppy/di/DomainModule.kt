package com.example.keeppy.di

import com.example.keeppy.data.database.TaskDatabase
import com.example.keeppy.data.repository.TaskRepositoryImpl
import com.example.keeppy.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDatabase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(taskDatabase.taskDAO)
    }
}