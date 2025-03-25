package com.iceman.domain.usecase

import com.iceman.data.repository.MathRepository

class GetMathChartUseCase(private val mathRepository: MathRepository)  {
     operator fun invoke() = mathRepository.getMathList()

}