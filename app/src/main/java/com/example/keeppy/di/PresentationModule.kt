package com.example.keeppy.di

import com.example.keeppy.domain.model.Task
import com.example.keeppy.domain.providers.StringResourseProvider
import com.example.keeppy.domain.providers.WorkerProvider
import com.example.keeppy.domain.repository.TaskRepository
import com.example.keeppy.domain.usecases.format.date.FormatDateUseCase
import com.example.keeppy.domain.usecases.format.date.FormatDateUseCaseImpl
import com.example.keeppy.domain.usecases.format.time.FormatTimeUseCase
import com.example.keeppy.domain.usecases.format.time.FormatTimeUseCaseImpl
import com.example.keeppy.domain.usecases.get.GetTaskByIdUseCase
import com.example.keeppy.domain.usecases.get.GetTaskByIdUseCaseImpl
import com.example.keeppy.domain.usecases.get_all.GetAllTaskUseCase
import com.example.keeppy.domain.usecases.get_all.GetAllTaskUseCaseImpl
import com.example.keeppy.domain.usecases.save.SaveTaskUseCase
import com.example.keeppy.domain.usecases.save.SaveTaskUseCaseImpl
import com.example.keeppy.domain.usecases.schedule.cancle.CancelScheduleTaskUseCase
import com.example.keeppy.domain.usecases.schedule.cancle.CancelScheduleTaskUseCaseImpl
import com.example.keeppy.domain.usecases.schedule.schedule.ScheduleTaskUseCase
import com.example.keeppy.domain.usecases.schedule.schedule.ScheduleTaskUseCaseImpl
import com.example.keeppy.domain.usecases.task.delete.DeleteTaskUseCase
import com.example.keeppy.domain.usecases.task.delete.DeleteTaskUseCaseImpl
import com.example.keeppy.domain.usecases.task.edit.EditTaskUseCase
import com.example.keeppy.domain.usecases.task.edit.EditTaskUseCaseImpl
import com.example.keeppy.domain.usecases.validate.input.ValidateInputsUseCase
import com.example.keeppy.domain.usecases.validate.input.ValidateInputsUseCaseImpl
import com.example.keeppy.domain.usecases.validate.schedule.ValidateScheduleTimeUseCase
import com.example.keeppy.domain.usecases.validate.schedule.ValidateScheduleTimeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(repository: TaskRepository): GetAllTaskUseCase {
        return GetAllTaskUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSaveTaskUseCase(
        repository: TaskRepository,
        scheduleTaskUseCase: ScheduleTaskUseCase,
        cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
        validateInputsUseCase: ValidateInputsUseCase,
        validateScheduleTimeUseCase: ValidateScheduleTimeUseCase
    ): SaveTaskUseCase {
        return SaveTaskUseCaseImpl(
            repository,
            scheduleTaskUseCase,
            cancelScheduleTaskUseCase,
            validateInputsUseCase,
            validateScheduleTimeUseCase
        )
    }

    @Provides
    @Singleton
    fun provideSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): ScheduleTaskUseCase {
        return ScheduleTaskUseCaseImpl(workerProvider)
    }


    @Provides
    @Singleton
    fun provideCancelSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): CancelScheduleTaskUseCase {
        return CancelScheduleTaskUseCaseImpl(workerProvider)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(repository: TaskRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCaseImpl(repository)
    }


    @Provides
    @Singleton
    fun ValidateInputsUseCase(stringResourseProvider: StringResourseProvider): ValidateInputsUseCase {
        return ValidateInputsUseCaseImpl(stringResourseProvider)
    }


    @Provides
    @Singleton
    fun ValidateScheduleTimeUseCase(stringResourseProvider: StringResourseProvider): ValidateScheduleTimeUseCase {
        return ValidateScheduleTimeUseCaseImpl(stringResourseProvider)
    }


    @Provides
    @Singleton
    fun provideEditTaskUseCase(
        repository: TaskRepository,
        scheduleTaskUseCase: ScheduleTaskUseCase,
        cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
        validateInputsUseCase: ValidateInputsUseCase,
        validateScheduleTimeUseCase: ValidateScheduleTimeUseCase
    ): EditTaskUseCase {
        return EditTaskUseCaseImpl(
            repository,
            scheduleTaskUseCase,
            cancelScheduleTaskUseCase,
            validateInputsUseCase,
            validateScheduleTimeUseCase
        )
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(
        repository: TaskRepository,
        cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
    ): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(repository, cancelScheduleTaskUseCase)
    }


    @Provides
    @Singleton
    fun provideFormatDateUseCase(): FormatDateUseCase {
        return FormatDateUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideFormatTimeUseCase(): FormatTimeUseCase {
        return FormatTimeUseCaseImpl()
    }


}