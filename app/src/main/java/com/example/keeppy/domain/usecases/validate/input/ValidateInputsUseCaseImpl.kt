package com.example.keeppy.domain.usecases.validate.input

import com.example.keeppy.common.exceptions.TaskException
import com.example.keeppy.domain.providers.StringResourseProvider
import javax.inject.Inject

class ValidateInputsUseCaseImpl @Inject constructor(
    private val ValidateInputsUseCase: StringResourseProvider
) : ValidateInputsUseCase{

    override fun invoke(vararg inputs: String) {
        inputs.forEach { input ->
            if (input.isBlank()) {
                throw TaskException("Fill in All Fields")
            }
        }
    }

}