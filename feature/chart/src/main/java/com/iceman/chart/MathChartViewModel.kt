package com.iceman.chart

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.domain.usecase.ClearMathListUseCase
import com.iceman.domain.usecase.DeleteMathUseCase
import com.iceman.domain.usecase.GetMathChartUseCase
import com.iceman.network.response.CalculateMathResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MathChartViewModel(
    private val getMathChartUseCase: GetMathChartUseCase,
    private val deleteMathUseCase: DeleteMathUseCase,
    private val clearMathListUseCase: ClearMathListUseCase,
) : ViewModel() {

    val fetchMathList: StateFlow<List<CalculateMathResponse>> =
        getMathChartUseCase.invoke().stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    fun deleteMath(id: Int, context: Context) {
        viewModelScope.launch {
            deleteMathUseCase.invoke(id) { code, error ->
                if (code && error.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        "Item $id foi removido com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Erro inesperado ao tentar remover item, por favor tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

        }
    }

    fun clearMathChart(context: Context) {
        viewModelScope.launch {
            clearMathListUseCase.invoke { code, error ->
                if (code && error.isNullOrEmpty()) {
                    Toast.makeText(context, "Lista excluida com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        context,
                        "Erro inesperado, por favor tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}