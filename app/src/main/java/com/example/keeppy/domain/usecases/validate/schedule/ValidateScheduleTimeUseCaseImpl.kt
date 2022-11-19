package com.example.keeppy.domain.usecases.validate.schedule

import com.example.keeppy.common.exceptions.TaskException
import com.example.keeppy.common.utils.Utils
import com.example.keeppy.domain.model.Status
import com.example.keeppy.domain.providers.StringResourseProvider
import java.util.Calendar
import javax.inject.Inject

class ValidateScheduleTimeUseCaseImpl @Inject constructor(
    private val stringResourceProvider: StringResourseProvider
) : ValidateScheduleTimeUseCase {


    override fun invoke(time: String, date: String, status: Status): Long {

        val nowTime = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        val dueTime = Utils.convertStringToCalendar(date, time)

        val timeInMillisTask = dueTime.timeInMillis - nowTime.timeInMillis

        if (status == Status.TODO) {
            if (timeInMillisTask <= 0) {
                throw TaskException("Cannot Scheduled to the Past")
            }
        }
        return timeInMillisTask
    }


}