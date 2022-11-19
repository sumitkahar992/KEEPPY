package com.example.keeppy.domain.usecases.schedule.schedule

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.providers.WorkerProvider
import javax.inject.Inject


class ScheduleTaskUseCaseImpl @Inject constructor(
    private val workProvider: WorkerProvider<Task>
) : ScheduleTaskUseCase {


    override fun invoke(task: Task, delayInMillis: Long) {
        workProvider.createWork(task, delayInMillis)
    }

}