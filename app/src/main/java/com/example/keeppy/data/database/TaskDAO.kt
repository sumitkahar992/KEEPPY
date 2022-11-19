package com.example.keeppy.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.keeppy.data.entity.StatusDTO
import com.example.keeppy.data.entity.TaskEntity
import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAllTask() : Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int) : TaskEntity?

    @Query("SELECT * FROM task WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchTask(query: String) : Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE status = :filter")
    fun filterTask(filter: StatusDTO) : Flow<List<TaskEntity>>

    @Insert
    suspend fun saveTask(task: TaskEntity) : Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)






}