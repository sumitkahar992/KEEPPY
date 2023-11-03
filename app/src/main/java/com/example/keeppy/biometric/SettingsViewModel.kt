package com.example.keeppy.biometric

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeppy.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appBioMetricManager: AppBioMetricManager,
    private val dataStoreRepo: DataStoreRepository,
) : ViewModel() {

    private val _biometricAuthState = MutableStateFlow(false)
    val biometricAuthState: StateFlow<Boolean> = _biometricAuthState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.getBiometricAuthKey.collect {
                _biometricAuthState.value = it
            }
        }
    }

    fun showBiometricPrompt(activity: Activity) {
        appBioMetricManager.initBiometricPrompt(
            activity = activity,
            listener = object : BiometricAuthListener {

                override fun onBioMetricAuthSuccess() {
                    viewModelScope.launch {
                        dataStoreRepo.saveBiometricAuthKey(!_biometricAuthState.value)
                    }
                }

                override fun onErrorOccurred() {

                }

                override fun onUserCancelled() {

                }
            }
        )
    }
}

























