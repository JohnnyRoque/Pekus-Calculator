package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository

class ClearMathListUseCase(private val mathRepository: MathRepository) {
    suspend operator fun invoke(
        callback: (Boolean, String?) -> Unit
    ) {
        return mathRepository.clearMathList(callback)
    }
}