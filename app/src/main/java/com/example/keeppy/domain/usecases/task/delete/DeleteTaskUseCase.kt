package com.example.keeppy.domain.usecases.task.delete

import com.example.keeppy.domain.model.Task

interface DeleteTaskUseCase {

    suspend  operator fun invoke(task: Task)

}