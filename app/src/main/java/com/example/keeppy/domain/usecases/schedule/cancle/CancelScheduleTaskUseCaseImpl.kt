package com.example.keeppy.domain.usecases.schedule.cancle

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.providers.WorkerProvider
import javax.inject.Inject

class CancelScheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: WorkerProvider<Task>
) : CancelScheduleTaskUseCase{


    override fun invoke(task: Task) {
        workerProvider.cancelWork(task)
    }

}