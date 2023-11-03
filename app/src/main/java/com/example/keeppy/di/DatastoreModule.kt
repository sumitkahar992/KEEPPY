package com.example.keeppy.di

import android.content.Context
import com.example.keeppy.biometric.AppBioMetricManager
import com.example.keeppy.biometric.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {


    @Provides
    fun providesDataStoreRepository(@ApplicationContext context: Context) =
        DataStoreRepository(context)

    @Provides
    fun provideAppBioMetricManager(@ApplicationContext context: Context) =
        AppBioMetricManager(context)


}