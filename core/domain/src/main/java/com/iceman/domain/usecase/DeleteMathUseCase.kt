package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository

class DeleteMathUseCase(private val mathRepository: MathRepository) {
    suspend operator fun invoke(
        id: Int, callback: (Boolean, String?) -> Unit
    ) = mathRepository.deleteMathCal(id,callback)

}