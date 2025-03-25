package com.iceman.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.domain.usecase.ClearMathListUseCase
import com.iceman.domain.usecase.DeleteMathUseCase
import com.iceman.domain.usecase.GetMathChartUseCase
import com.iceman.network.response.CalculateMathResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MathChartViewModel(
     getMathChartUseCase: GetMathChartUseCase,
    private val deleteMathUseCase: DeleteMathUseCase,
    private val clearMathListUseCase: ClearMathListUseCase,
) : ViewModel() {

    val fetchMathList: StateFlow<List<CalculateMathResponse>> =
        getMathChartUseCase.invoke().stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

}