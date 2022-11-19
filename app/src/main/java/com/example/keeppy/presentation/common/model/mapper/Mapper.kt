package com.example.keeppy.presentation.common.model.mapper

import com.example.keeppy.domain.model.Priority
import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.StatusView
import com.example.keeppy.presentation.common.model.TaskView


fun TaskView.mapToModel() = Task(
    id,title,date,time,description,
    priority.mapToModel(),
    status.mapToModel()
)

fun PriorityView.mapToModel() = when (this) {
    PriorityView.NONE -> Priority.NONE
    PriorityView.LOW -> Priority.LOW
    PriorityView.MEDIUM -> Priority.MEDIUM
    PriorityView.HIGH -> Priority.HIGH
}

fun StatusView.mapToModel() = when (this) {
    StatusView.DONE -> Status.DONE
    StatusView.TODO -> Status.TODO
    StatusView.PROGRESS -> Status.PROGRESS
}