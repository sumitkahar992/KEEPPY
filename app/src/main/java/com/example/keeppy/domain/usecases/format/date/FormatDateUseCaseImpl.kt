package com.example.keeppy.domain.usecases.format.date

import java.text.SimpleDateFormat
import java.util.*

class FormatDateUseCaseImpl : FormatDateUseCase {

    private val _formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    override fun invoke(date: String): String {
        return _formatter.format(((_formatter.parse(date))))
    }

}