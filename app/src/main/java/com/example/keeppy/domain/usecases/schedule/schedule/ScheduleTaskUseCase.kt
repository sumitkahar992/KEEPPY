package com.example.keeppy.domain.usecases.schedule.schedule

import com.example.keeppy.domain.model.Task

interface ScheduleTaskUseCase {

    operator fun invoke(task: Task, delayInMillis: Long)

}