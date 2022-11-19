package com.example.keeppy.domain.usecases.schedule.cancle

import com.example.keeppy.domain.model.Task

interface CancelScheduleTaskUseCase {

    operator fun invoke(task: Task)

}