package com.matatoa.wheremystuff.domain.di

import com.matatoa.wheremystuff.core.di.coreModules
import com.matatoa.wheremystuff.core.di.platformModule
import org.koin.core.module.Module

/**
 * All Koin modules needed by the domain layer
 */
fun domainModules(): List<Module> = buildList {
    // core modules reference
    add(platformModule())
    addAll(coreModules)

    // domain modules
    add(useCaseModule)
}