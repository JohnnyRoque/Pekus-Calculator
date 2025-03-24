package com.iceman.calculator.di

import com.iceman.calculator.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCalculatorModule = module{
    viewModel { CalculatorViewModel(get()) }
}