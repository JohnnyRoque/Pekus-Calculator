package com.iceman.network.di

import com.iceman.network.interceptor.PekusCalInterceptor
import com.iceman.network.service.NetworkDataSource
import com.iceman.network.service.PekusCalApiImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module

 val networkModule = module {

    single<NetworkDataSource> { PekusCalApiImpl() }

}

