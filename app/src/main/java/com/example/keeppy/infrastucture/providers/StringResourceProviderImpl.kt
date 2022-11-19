package com.example.keeppy.infrastucture.providers

import android.content.Context
import androidx.annotation.StringRes
import com.example.keeppy.domain.providers.StringResourseProvider
import javax.inject.Inject

class StringResourceProviderImpl @Inject constructor(
    private val context : Context
) : StringResourseProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

}