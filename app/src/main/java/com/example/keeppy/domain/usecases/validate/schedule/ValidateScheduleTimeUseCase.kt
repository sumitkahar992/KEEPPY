package com.example.keeppy.domain.usecases.validate.schedule

import com.example.keeppy.domain.model.Status

interface ValidateScheduleTimeUseCase {

    operator fun invoke(time: String, date: String, status: Status): Long

}