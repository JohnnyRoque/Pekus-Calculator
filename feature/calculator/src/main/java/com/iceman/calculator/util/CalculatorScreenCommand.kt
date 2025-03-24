package com.iceman.calculator.util

sealed class CalculatorScreenCommand {
    object Idle : CalculatorScreenCommand()
    data class SuccessAlert(val message: String) : CalculatorScreenCommand()
    object FailureAlert : CalculatorScreenCommand()
}