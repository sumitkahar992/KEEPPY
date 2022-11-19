package com.example.keeppy.domain.usecases.get_all

import com.example.keeppy.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTaskUseCase {

    suspend operator fun invoke(): Flow<List<Task>>

}