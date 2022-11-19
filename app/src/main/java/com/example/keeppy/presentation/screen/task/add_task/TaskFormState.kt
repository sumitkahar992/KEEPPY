package com.example.keeppy.presentation.screen.task.add_task

import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.TaskView

interface TaskFormState {
    val task: TaskView

    fun onTitleChange(title: String)
    fun onTimeChange(hour: Int, minute: Int)
    fun onDateChange(date: String)
    fun onDescriptionChange(description: String)
    fun onPriorityChange(priority: PriorityView)


}