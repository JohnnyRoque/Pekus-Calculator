package com.iceman.pekuscalculator.application

import android.app.Application
import com.iceman.calculator.di.featureCalculatorModule
import com.iceman.data.di.dataModule
import com.iceman.domain.di.domainModule
import com.iceman.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PCApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PCApplication)
            modules(
                networkModule,
                dataModule,
                domainModule,
                featureCalculatorModule
            )
        }
    }
}