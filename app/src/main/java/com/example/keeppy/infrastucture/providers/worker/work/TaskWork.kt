package com.example.keeppy.infrastucture.providers.worker.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.keeppy.infrastucture.notification.NotificationUtils

class TaskWork(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    override fun doWork(): Result {
        val taskData = inputData
        val taskTitle = taskData.getString(com.example.keeppy.common.utils.Constants.WORKER.KEYS.KEY_TITLE)
        val taskDescription = taskData.getString(com.example.keeppy.common.utils.Constants.WORKER.KEYS.KEY_DESCRIPTION)

        NotificationUtils.showNotification(applicationContext, taskTitle!!, taskDescription!!)

        return Result.success()
    }
}