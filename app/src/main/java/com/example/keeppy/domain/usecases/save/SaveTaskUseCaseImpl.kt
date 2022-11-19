package com.example.keeppy.domain.usecases.save

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import com.example.keeppy.domain.usecases.schedule.cancle.CancelScheduleTaskUseCase
import com.example.keeppy.domain.usecases.schedule.schedule.ScheduleTaskUseCase
import com.example.keeppy.domain.usecases.validate.input.ValidateInputsUseCase
import com.example.keeppy.domain.usecases.validate.schedule.ValidateScheduleTimeUseCase
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
    private val validateInputsUseCase: ValidateInputsUseCase,
    private val validateScheduleTimeUseCase: ValidateScheduleTimeUseCase

) : SaveTaskUseCase {


    override suspend operator fun invoke(task: Task) {
        validateInputsUseCase(task.title, task.description, task.date, task.time)

        validateScheduleTimeUseCase(
            task.time,
            task.date,
            task.status
        ).also { delayInMillisTaskWork ->

            repository.saveTask(task).also { idtask ->
                val taskSchedule = task.copy(id = idtask.toInt())

                cancelScheduleTaskUseCase(task = taskSchedule)
                scheduleTaskUseCase(task = taskSchedule, delayInMillis = delayInMillisTaskWork)
            }


        }


    }
}