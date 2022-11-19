package com.example.keeppy.data.repository

import com.example.keeppy.data.database.TaskDAO
import com.example.keeppy.data.mappers.mapFromModel
import com.example.keeppy.domain.mappers.mapToEntity
import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val _taskDAO : TaskDAO
) : TaskRepository {


    override fun getAllTask(): Flow<List<Task>> {
        return _taskDAO.getAllTask().map { taskEntities ->
            taskEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return _taskDAO.getTaskById(id)?.mapFromModel()
    }

    override suspend fun searchTask(query: String): Flow<List<Task>> {
        return _taskDAO.searchTask(query).map { taskEntities ->
            taskEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun filterTask(filter: Status): Flow<List<Task>> {
        val filterStatusDTO = filter.mapToEntity()
        return _taskDAO.filterTask(filterStatusDTO).map { taskEntities ->
            taskEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun saveTask(task: Task): Long {
        val taskEntity = task.mapToEntity()
        return _taskDAO.saveTask(taskEntity)
    }

    override suspend fun updateTask(task: Task) {
        val taskEntity = task.mapToEntity()
        return _taskDAO.updateTask(taskEntity)
    }

    override suspend fun deleteTask(task: Task) {
        val taskEntity = task.mapToEntity()
        return _taskDAO.deleteTask(taskEntity)
    }





}








































