package com.example.keeppy.domain.usecases.task.edit

import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import com.example.keeppy.domain.usecases.schedule.cancle.CancelScheduleTaskUseCase
import com.example.keeppy.domain.usecases.schedule.schedule.ScheduleTaskUseCase
import com.example.keeppy.domain.usecases.validate.input.ValidateInputsUseCase
import com.example.keeppy.domain.usecases.validate.schedule.ValidateScheduleTimeUseCase

class EditTaskUseCaseImpl constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
    private val validateInputsUseCase: ValidateInputsUseCase,
    private val validateScheduleTimeUseCase: ValidateScheduleTimeUseCase
) : EditTaskUseCase{


    override suspend fun invoke(task: Task) {
        validateInputsUseCase(task.title, task.description, task.date, task.time)

        if (task.status == Status.TODO) {
            validateScheduleTimeUseCase(task.time, task.date, task.status).also { delayInMillisTaskWork->
                scheduleTaskUseCase(task = task, delayInMillis = delayInMillisTaskWork)
            }
        }


        repository.updateTask(task)
        cancelScheduleTaskUseCase(task = task)



    }


}
