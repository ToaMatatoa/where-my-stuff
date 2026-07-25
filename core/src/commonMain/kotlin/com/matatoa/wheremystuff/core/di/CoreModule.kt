package com.matatoa.wheremystuff.core.di

import com.matatoa.wheremystuff.core.database.getPlaceDao
import com.matatoa.wheremystuff.core.database.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
    single { getRoomDatabase(builder = get()) }
    single { getPlaceDao(whereMyStuffDatabase = get()) }
}

/**
 * All Koin modules needed by the core layer
 */
val coreModules = listOf(
    provideDatabaseModule,
)
