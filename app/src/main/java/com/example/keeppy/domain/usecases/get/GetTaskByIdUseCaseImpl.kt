package com.example.keeppy.domain.usecases.get

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : GetTaskByIdUseCase{


    override suspend fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }


}