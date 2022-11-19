package com.example.keeppy.domain.usecases.validate.input

interface ValidateInputsUseCase {

    operator fun invoke(vararg inputs: String)

}