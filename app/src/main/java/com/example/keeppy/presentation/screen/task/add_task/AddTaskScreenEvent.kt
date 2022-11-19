package com.example.keeppy.presentation.screen.task.add_task

sealed class AddTaskScreenEvent {
    object SaveTaskSuccess : AddTaskScreenEvent()
    data class SaveTaskFailed(val message: String) : AddTaskScreenEvent()
}
