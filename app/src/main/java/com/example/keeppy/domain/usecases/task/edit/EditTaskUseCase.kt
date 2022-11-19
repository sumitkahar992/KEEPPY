package com.example.keeppy.domain.usecases.task.edit

import com.example.keeppy.domain.model.Task

interface EditTaskUseCase {

    suspend operator fun invoke(task: Task)

}