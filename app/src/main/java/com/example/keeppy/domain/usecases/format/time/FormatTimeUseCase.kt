package com.example.keeppy.domain.usecases.format.time

interface FormatTimeUseCase {

    operator fun invoke(hour: Int, minute: Int): String

}