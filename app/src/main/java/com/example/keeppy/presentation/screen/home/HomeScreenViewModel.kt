package com.example.keeppy.presentation.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeppy.domain.mappers.mapToView
import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.usecases.get_all.GetAllTaskUseCase
import com.example.keeppy.domain.usecases.task.delete.DeleteTaskUseCase
import com.example.keeppy.presentation.common.model.TaskView
import com.example.keeppy.presentation.common.model.mapper.mapToModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {


    private val _uiState: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    private val _tasksCache: MutableState<List<TaskView>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            getAllTaskUseCase().map { taskModel ->
                taskModel.map { taskModel -> taskModel.mapToView() }
            }.collect { tasks ->
                _tasksCache.value = tasks
                _uiState.value = _uiState.value.copy(tasks = tasks)

            }
        }
    }

    fun onDeleteTask() {
        viewModelScope.launch {
            val task = _uiState.value.taskClickedDelete
            deleteTaskUseCase(task.mapToModel())
        }
    }

    fun onSetTaskDeleted(task: TaskView) {
        _uiState.value = _uiState.value.copy(taskClickedDelete = task)
    }














































}