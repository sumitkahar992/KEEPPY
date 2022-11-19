package com.example.keeppy.presentation.screen.task.edit_task

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.example.keeppy.presentation.screen.task.add_task.TaskForm

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTaskForm(
    modifier: Modifier = Modifier,
    editTaskFormState: EditTaskFormState,
    keyboardController: SoftwareKeyboardController?
) {

    TaskForm(
        taskFormState = editTaskFormState,
        keyboardController = keyboardController,
        modifier = modifier
    )

}