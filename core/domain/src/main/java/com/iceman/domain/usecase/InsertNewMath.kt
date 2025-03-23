package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository
import com.iceman.network.request.MathRequest

class InsertNewMath(private val mathRepository: MathRepository) {

    internal suspend fun invoke(
        insertNewMathRequest: MathRequest,
        callback: (Boolean, String?) -> Unit
    ) = mathRepository.calculateNewMath(insertNewMathRequest, callback)

}