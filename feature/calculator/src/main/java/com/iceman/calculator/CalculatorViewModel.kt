package com.iceman.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val insertNewMathUseCase: InsertNewMathUseCase,
) : ViewModel() {

    private var _selectedArithmetic: MutableStateFlow<Arithmetics?> = MutableStateFlow(null)
    val selectedArithmetic = _selectedArithmetic.asStateFlow()

    private var _openDialog by mutableStateOf(true)
    val openDialog = _openDialog

    private val mutableCommand: MutableStateFlow<CalculatorScreenCommand> =
        MutableStateFlow(CalculatorScreenCommand.Idle)
    val command: StateFlow<CalculatorScreenCommand> = mutableCommand.asStateFlow()

    private val _valueA: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    private val valueA: StateFlow<TextFieldValue> = _valueA.asStateFlow()

    private val _valueB: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    private val valueB: StateFlow<TextFieldValue> = _valueB.asStateFlow()

    val mathValues: StateFlow<Pair<TextFieldValue, TextFieldValue>> =
        combine(valueA, valueB) { a, b ->
            Pair(a, b)
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), Pair(
                TextFieldValue(),
                TextFieldValue()
            )
        )

    val isButtonEnabled: StateFlow<Boolean> =
        combine(mathValues, selectedArithmetic) { values, arithmetic ->
            val digitRegex = "^\\d+$".toRegex()
            values.first.text.matches(digitRegex) && values.second.text.matches(digitRegex) && arithmetic != null
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun updateMathValue(textFieldValue: TextFieldValue, isValueA: Boolean) {
        if (isValueA) {
            _valueA.update { textFieldValue }
        } else {
            _valueB.update { textFieldValue }
        }

    }


    fun selectArithmetic(arithmetic: Arithmetics) {
        _selectedArithmetic.update { arithmetic }
    }

    fun calculateValues(
        arithmetic: Arithmetics,
        valueA: Double,
        valueB: Double,
    ) {
        viewModelScope.launch {
            insertNewMathUseCase.invoke(
                MathRequest(
                    id = 0,
                    valorA = valueA,
                    valorB = valueB,
                    operation = arithmetic.symbol,
                    result = arithmeticResult(valueA, valueB, arithmetic),
                    date = ""
                ).makeNewMathRequest()
            ) { code, result, error ->
                if (code && result != null) {
                    CalculatorScreenCommand.SuccessAlert(result).run()
                } else {
                    CalculatorScreenCommand.FailureAlert.run()
                }
            }
        }
    }

    fun clearValues() {
        CalculatorScreenCommand.Idle.run()
        _valueA.update { TextFieldValue() }
        _valueB.update { TextFieldValue() }
        _openDialog = false
    }

    private fun CalculatorScreenCommand.run() {
        mutableCommand.update { this }
    }
}