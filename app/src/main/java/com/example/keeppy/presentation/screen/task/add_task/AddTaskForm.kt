package com.example.keeppy.presentation.screen.task.add_task

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskForm(
    modifier: Modifier = Modifier,
    addTaskFormState: AddTaskFormState,
    keyboardController: SoftwareKeyboardController?
) {
    TaskForm(
        taskFormState = addTaskFormState,
        keyboardController = keyboardController,
        modifier = modifier
    )

}