package com.example.keeppy.presentation.screen.task.edit_task

sealed class EditTaskScreenEvent {
    object EditTaskSuccess : EditTaskScreenEvent()
    data class EditTaskFailed(val message: String) : EditTaskScreenEvent()
}
