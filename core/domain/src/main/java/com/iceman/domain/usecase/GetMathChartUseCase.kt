package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class GetMathChartUseCase(private val mathRepository: MathRepository) {
    operator fun invoke() = flow {
        while (currentCoroutineContext().isActive)
        emit(
            mathRepository.getMathList()
        )
    }

}