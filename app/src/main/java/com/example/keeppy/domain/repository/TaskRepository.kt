package com.example.keeppy.domain.repository

import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTask() : Flow<List<Task>>

    suspend fun getTaskById(id: Int) : Task?

    suspend fun searchTask(query: String) : Flow<List<Task>>

    suspend fun filterTask(filter: Status) : Flow<List<Task>>

    suspend fun saveTask(task: Task) : Long

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)





}