package com.iceman.domain.util

import com.iceman.domain.model.Arithmetics

fun arithmeticResult(valueA : Double,valueB: Double,arithmetic: Arithmetics): Double {
   return when(arithmetic){
        Arithmetics.ADD -> {valueA + valueB}
        Arithmetics.SUBTRACTION -> { valueA - valueB }
        Arithmetics.TIMES -> {valueA * valueB}
        Arithmetics.DIVISION -> {valueA / valueB}
    }
}