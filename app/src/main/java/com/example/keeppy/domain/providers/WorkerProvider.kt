package com.example.keeppy.domain.providers

interface WorkerProvider<T> {

    fun createWork(data: T, delayInMillis: Long)

    fun cancelWork(data : T)

}