package com.example.keeppy.biometric

interface BiometricAuthListener {
    fun onBioMetricAuthSuccess()
    fun onUserCancelled()
    fun onErrorOccurred()
}