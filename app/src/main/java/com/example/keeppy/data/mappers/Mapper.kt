package com.example.keeppy.data.mappers

import com.example.keeppy.data.entity.PriorityDTO
import com.example.keeppy.data.entity.StatusDTO
import com.example.keeppy.data.entity.TaskEntity
import com.example.keeppy.domain.model.Priority
import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task


fun TaskEntity.mapFromModel() = Task(
    id,
    title,
    date,
    time,
    description,
    priority.mapFromModel(),
    status.mapFromModel()
)

fun PriorityDTO.mapFromModel() = when (this) {
    PriorityDTO.NONE -> Priority.NONE
    PriorityDTO.LOW -> Priority.LOW
    PriorityDTO.MEDIUM -> Priority.MEDIUM
    PriorityDTO.HIGH -> Priority.HIGH
}

fun StatusDTO.mapFromModel() = when (this) {
    StatusDTO.TODO -> Status.TODO
    StatusDTO.PROGRESS -> Status.PROGRESS
    StatusDTO.DONE -> Status.DONE
}