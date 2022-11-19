package com.example.keeppy.domain.usecases.get_all

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : GetAllTaskUseCase {


    override suspend fun invoke(): Flow<List<Task>> {
        return repository.getAllTask()
    }

}