package com.example.keeppy

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeepyApplication : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration =

        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()


}