package com.iceman.domain.usecase

import com.iceman.data.extension.makeNewMathRequest
import com.iceman.data.repository.MathRepository
import com.iceman.network.request.MathRequest

class InsertNewMathUseCase(private val mathRepository: MathRepository) {

    suspend operator fun invoke(
        insertNewMathRequest: MathRequest,
        callback: (Boolean, result: String? ,String?) -> Unit
    ) = mathRepository.calculateNewMath(insertNewMathRequest, callback)

}