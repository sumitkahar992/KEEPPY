package com.example.keeppy.domain.providers

interface StringResourseProvider {

    fun getString(stringResId: Int) : String

}