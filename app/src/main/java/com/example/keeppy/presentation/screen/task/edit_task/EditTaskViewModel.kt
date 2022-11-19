package com.example.keeppy.presentation.screen.task.edit_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeppy.common.exceptions.TaskException
import com.example.keeppy.common.utils.emptyString
import com.example.keeppy.domain.mappers.mapToView
import com.example.keeppy.domain.usecases.format.date.FormatDateUseCase
import com.example.keeppy.domain.usecases.format.time.FormatTimeUseCase
import com.example.keeppy.domain.usecases.get.GetTaskByIdUseCase
import com.example.keeppy.domain.usecases.task.edit.EditTaskUseCase
import com.example.keeppy.presentation.common.model.PriorityView
import com.example.keeppy.presentation.common.model.StatusView
import com.example.keeppy.presentation.common.model.TaskView
import com.example.keeppy.presentation.common.model.mapper.mapToModel
import com.example.keeppy.presentation.screen.task.add_task.AddTaskScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val formatTimeUseCase: FormatTimeUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase

) : ViewModel() {


    private val _task: MutableState<TaskView> = mutableStateOf(TaskView())
    val task: State<TaskView> get() = _task

    private val _uiEvent: MutableSharedFlow<EditTaskScreenEvent> = MutableSharedFlow()
    val uiEvent: SharedFlow<EditTaskScreenEvent> get() = _uiEvent

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            viewModelScope.launch {
                getTaskByIdUseCase(taskId)?.also { task ->
                    _task.value = task.mapToView()
                }
            }
        }
    }

    fun onEditTask() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                editTaskUseCase(_task.value.mapToModel())
                _uiEvent.emit(EditTaskScreenEvent.EditTaskSuccess)
            }catch (e: TaskException) {
                _uiEvent.emit(EditTaskScreenEvent.EditTaskFailed(e.message ?: emptyString()))
            }
        }
    }

    fun onTitleChange(title: String) {
        _task.value = _task.value.copy(title = title)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val timeFormatted = formatTimeUseCase(hour, minute)
        _task.value = _task.value.copy(time = timeFormatted)
    }

    fun onDateChange(date: String) {
        val dateFormatted = formatDateUseCase(date)
        _task.value = _task.value.copy(date = dateFormatted)
    }

    fun onDescriptionChange(description: String) {
        _task.value = _task.value.copy(description = description)
    }

    fun onPriorityChange(priority: PriorityView) {
        _task.value = _task.value.copy(priority = priority)
    }

    fun onStatusChange(status: StatusView) {
        _task.value = _task.value.copy(status = status)
    }


}



























