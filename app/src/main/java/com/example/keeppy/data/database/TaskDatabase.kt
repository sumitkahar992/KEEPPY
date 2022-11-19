package com.example.keeppy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keeppy.data.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    exportSchema = false,
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDAO: TaskDAO

}