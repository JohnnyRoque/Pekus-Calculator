package com.iceman.chart.di

import com.iceman.chart.MathChartViewModel
import org.koin.dsl.module

val chartModule = module {
    single { MathChartViewModel(get(), get(), get()) }
}