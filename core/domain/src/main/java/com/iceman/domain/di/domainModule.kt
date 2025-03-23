package com.iceman.domain.di

import com.iceman.domain.usecase.ClearMathListUseCase
import com.iceman.domain.usecase.DeleteMathUseCase
import com.iceman.domain.usecase.GetMathChartUseCase
import com.iceman.domain.usecase.InsertNewMathUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetMathChartUseCase(get()) }
    single { DeleteMathUseCase(get()) }
    single { InsertNewMathUseCase(get()) }
    single { ClearMathListUseCase(get()) }
}