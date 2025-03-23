package com.iceman.domain.di

import com.iceman.domain.usecase.ClearMathList
import com.iceman.domain.usecase.DeleteMathUseCase
import com.iceman.domain.usecase.GetMathChartUseCase
import com.iceman.domain.usecase.InsertNewMath
import org.koin.dsl.module

val domainModule = module {
    single { GetMathChartUseCase(get()) }
    single { DeleteMathUseCase(get()) }
    single { InsertNewMath(get()) }
    single { ClearMathList(get()) }

}