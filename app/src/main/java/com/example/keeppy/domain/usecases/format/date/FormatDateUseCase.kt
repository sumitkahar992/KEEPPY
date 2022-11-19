package com.example.keeppy.domain.usecases.format.date

interface FormatDateUseCase {

    operator fun invoke(date: String) : String

}