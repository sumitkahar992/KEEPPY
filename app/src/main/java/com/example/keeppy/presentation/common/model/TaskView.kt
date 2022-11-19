package com.example.keeppy.presentation.common.model

import com.example.keeppy.common.utils.emptyString
import com.example.keeppy.domain.model.Priority
import com.example.keeppy.domain.model.Status

data class TaskView(
    val id: Int = 0,
    val title: String = emptyString(),
    val date: String = emptyString(),
    val time: String = emptyString(),
    val description: String = emptyString(),

    val priority: PriorityView = PriorityView.NONE,
    val status: StatusView = StatusView.TODO
)
