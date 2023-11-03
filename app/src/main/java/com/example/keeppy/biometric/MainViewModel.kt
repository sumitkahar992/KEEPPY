package com.example.keeppy.biometric

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeppy.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appBioMetricManager: AppBioMetricManager,
    private val dataStoreRepo: DataStoreRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _initAuth = MutableStateFlow(false)
    val initAuth: StateFlow<Boolean> = _initAuth.asStateFlow()

    private val _finishActivity = MutableStateFlow(false)
    val finishActivity: StateFlow<Boolean> = _finishActivity.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.getBiometricAuthKey.collect { biometricAuthState ->
                _initAuth.value = biometricAuthState
                if (biometricAuthState && appBioMetricManager.canAuthenticate()) {
                    _initAuth.emit(true)
                } else {
                    delay(1_000L)
                    _loading.emit(false)
                }
            }
        }

    }

    fun showBiometricPrompt(mainActivity: MainActivity) {
        appBioMetricManager.initBiometricPrompt(
            activity = mainActivity,
            listener = object : BiometricAuthListener {
                override fun onBioMetricAuthSuccess() {
                    viewModelScope.launch {
                        _loading.emit(false)
                    }
                    Log.d("BIO", "biometricAuthState: ${_initAuth.value}")
                }

                override fun onUserCancelled() {
                    finishActivity()
                }

                override fun onErrorOccurred() {
                    finishActivity()
                }
            }
        )
    }

    private fun finishActivity() {
        viewModelScope.launch {
            _finishActivity.emit(true)
        }
    }


}