package com.example.keeppy.presentation.screen.task.edit_task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.StatusView
import com.example.keeppy.presentation.common.model.TaskView
import com.example.keeppy.presentation.screen.task.add_task.TaskFormState

sealed interface EditTaskFormState : TaskFormState {
    fun onEditTask()
    fun onStatusChange(status: StatusView)
}

class EditTaskFormStateImpl(
    private val editTaskViewModel: EditTaskViewModel
) : EditTaskFormState {


    override val task: TaskView get() = editTaskViewModel.task.value

    override fun onEditTask() {
        editTaskViewModel.onEditTask()
    }

    override fun onStatusChange(status: StatusView) {
        editTaskViewModel.onStatusChange(status)
    }

    override fun onTitleChange(title: String) {
        editTaskViewModel.onTitleChange(title)
    }

    override fun onTimeChange(hour: Int, minute: Int) {
        editTaskViewModel.onTimeChange(hour, minute)
    }

    override fun onDateChange(date: String) {
        editTaskViewModel.onDateChange(date)
    }

    override fun onDescriptionChange(description: String) {
        editTaskViewModel.onDescriptionChange(description)
    }

    override fun onPriorityChange(priority: PriorityView) {
        editTaskViewModel.onPriorityChange(priority)
    }

}

@Composable
fun rememberEditTaskFormState(
    editTaskViewModel: EditTaskViewModel = hiltViewModel()
): EditTaskFormState = remember {

    EditTaskFormStateImpl(
        editTaskViewModel = editTaskViewModel
    )
}
























