package com.example.keeppy.domain.usecases.get

import com.example.keeppy.domain.model.Task

interface GetTaskByIdUseCase {

    suspend operator fun invoke(id:Int): Task?

}