package com.example.keeppy.infrastucture.providers.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.keeppy.common.utils.Constants
import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.providers.WorkerProvider
import com.example.keeppy.infrastucture.providers.worker.work.TaskWork
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TaskWorkerProviderImpl @Inject constructor(context: Context) : WorkerProvider<Task> {

    private val _workManager = WorkManager.getInstance(context)
    private val _workConstraints = Constraints.Builder().build()

    override fun createWork(data: Task, delayInMillis: Long) {
        val taskData = Data.Builder()
            .putString(Constants.WORKER.KEYS.KEY_TITLE, data.title)
            .putString(Constants.WORKER.KEYS.KEY_DESCRIPTION, data.description)
            .build()

        val taskWorkRequest = OneTimeWorkRequestBuilder<TaskWork>()
            .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
            .setConstraints(_workConstraints)
            .setInputData(taskData)
            .addTag("TASK_WORKER_N${data.id}")
            .build()

        _workManager.enqueue(taskWorkRequest)
    }

    override fun cancelWork(data: Task) {
        _workManager.cancelAllWorkByTag("TASK_WORKER_N${data.id}")
    }
}