package com.example.keeppy.di

import android.app.Application
import com.example.keeppy.domain.model.Task
import com.example.keeppy.infrastucture.providers.StringResourceProviderImpl
import com.example.keeppy.domain.providers.StringResourseProvider
import com.example.keeppy.infrastucture.providers.worker.TaskWorkerProviderImpl
import com.example.keeppy.domain.providers.WorkerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {


    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourseProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideTaskWorkerProvider(application: Application): WorkerProvider<Task> {
        return TaskWorkerProviderImpl(application)
    }

}