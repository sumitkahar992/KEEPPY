package com.example.keeppy.domain.usecases.task.delete

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import com.example.keeppy.domain.usecases.schedule.cancle.CancelScheduleTaskUseCase
import javax.inject.Inject

class DeleteTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
) : DeleteTaskUseCase{

    override suspend fun invoke(task: Task) {
        repository.deleteTask(task)
        cancelScheduleTaskUseCase(task)
    }



}