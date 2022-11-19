package com.example.keeppy.presentation.screen.home

import com.example.keeppy.presentation.common.model.TaskView

data class HomeScreenState(
    val tasks: List<TaskView> = emptyList(),
    val taskClickedDelete: TaskView = TaskView()
)
