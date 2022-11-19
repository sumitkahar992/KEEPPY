package com.example.keeppy.domain.usecases.save

import com.example.keeppy.domain.model.Task

interface SaveTaskUseCase {

    suspend operator fun invoke(task: Task)
}