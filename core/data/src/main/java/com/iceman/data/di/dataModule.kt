package com.iceman.data.di

import com.iceman.data.repository.MathRepository
import com.iceman.network.di.networkModule
import org.koin.dsl.module

 val dataModule = module {
    single{ MathRepository(get()) }
}

fun loadModule() = listOf(dataModule, networkModule)

