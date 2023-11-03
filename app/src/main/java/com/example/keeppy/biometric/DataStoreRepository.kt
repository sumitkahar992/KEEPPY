package com.example.keeppy.biometric

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("datastore_preferences")
        val THEME_MODE_KEY = booleanPreferencesKey("dark_mode")
        val IS_BIOMETRIC_AUTH_SET_KEY = booleanPreferencesKey("biometric_auth")
    }


    // Read data from DataStore
    val getThemeModeKey: Flow<Boolean> = context.dataStore.data.map { preference ->
        preference[THEME_MODE_KEY] ?: false
    }


    // Write data to DataStore
    suspend fun saveThemeModeKey(mode: Boolean) {
        context.dataStore.edit { preference ->
            preference[THEME_MODE_KEY] = mode
        }
    }

    val getBiometricAuthKey: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_BIOMETRIC_AUTH_SET_KEY] ?: false
    }

    suspend fun saveBiometricAuthKey(key: Boolean) {
        context.dataStore.edit { preference ->
            preference[IS_BIOMETRIC_AUTH_SET_KEY] = key
        }
    }


}

