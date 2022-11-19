package com.example.keeppy.presentation.screen.task.add_task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.TaskView


sealed interface AddTaskFormState : TaskFormState {
    fun onSaveTask()
}


class AddTaskFormStateImpl(
    private val addTaskViewModel: AddTaskViewModel
): AddTaskFormState {

    override fun onSaveTask() {
        addTaskViewModel.onSaveTask()
    }

    override val task: TaskView get() = addTaskViewModel.task.value

    override fun onTitleChange(title: String) {
        addTaskViewModel.onTitleChange(title)
    }

    override fun onTimeChange(hour: Int, minute: Int) {
        addTaskViewModel.onTimeChange(hour, minute)
    }

    override fun onDateChange(date: String) {
       addTaskViewModel.onDateChange(date)
    }

    override fun onDescriptionChange(description: String) {
        addTaskViewModel.onDescriptionChange(description)
    }

    override fun onPriorityChange(priority: PriorityView) {
        addTaskViewModel.onPriorityChange(priority)
    }
}

@Composable
fun rememberAddTaskFormState(
    addTaskViewModel: AddTaskViewModel
): AddTaskFormState = remember {
    AddTaskFormStateImpl(
        addTaskViewModel = addTaskViewModel
    )
}
