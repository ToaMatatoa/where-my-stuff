package com.matatoa.wheremystuff.di

import com.matatoa.wheremystuff.domain.di.domainModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        viewModelModule,
        *domainModules().toTypedArray(),
    )
}
