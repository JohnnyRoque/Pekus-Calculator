package com.iceman.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iceman.calculator.util.CalculatorScreenCommand
import com.iceman.data.extension.makeNewMathRequest
import com.iceman.domain.model.Arithmetics
import com.iceman.domain.usecase.InsertNewMathUseCase
import com.iceman.domain.util.arithmeticResult
import com.iceman.network.request.MathRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val insertNewMathUseCase: InsertNewMathUseCase,
) : ViewModel() {

    private var _selectedArithmetic = mutableStateOf(Arithmetics.ADD)
     val selectedArithmetic = _selectedArithmetic.value


    private val mutableCommand : MutableStateFlow<CalculatorScreenCommand?> = MutableStateFlow(null)
    val command: StateFlow<CalculatorScreenCommand?> = mutableCommand.asStateFlow()

    private val _mathValues: MutableStateFlow<Pair<TextFieldValue, TextFieldValue>> =
        MutableStateFlow(
            Pair(TextFieldValue(), TextFieldValue())
        )
    val mathValues: StateFlow<Pair<TextFieldValue, TextFieldValue>> = _mathValues.asStateFlow()


    fun selectArithmetic(arithmetic: Arithmetics){
        _selectedArithmetic.value = arithmetic
    }
    fun calculateValues(
        arithmetic: Arithmetics,
        valueA: Double,
        valueB: Double,
    ) {
        viewModelScope.launch {
            insertNewMathUseCase.invoke(
                MathRequest(
                    valorA = valueA,
                    valorB = valueB,
                    operation = arithmetic.symbol,
                    result = arithmeticResult(valueA, valueB, arithmetic)
                ).makeNewMathRequest()
            ){ code,result,error ->
                if (code && result != null){
                    CalculatorScreenCommand.SuccessAlert(result).run()
                }else{
                    CalculatorScreenCommand.FailureAlert.run()
                }
            }
        }

    }

    fun clearValues() {
        _mathValues.update { Pair(TextFieldValue(), TextFieldValue()) }
    }

    private fun CalculatorScreenCommand.run() {
        mutableCommand.update { this }
    }
}