package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository

class ClearMathList(private val mathRepository: MathRepository) {
    internal suspend fun invoke(
        callback: (Boolean, String?) -> Unit
    ) {
        return mathRepository.clearMathList(callback)
    }
}