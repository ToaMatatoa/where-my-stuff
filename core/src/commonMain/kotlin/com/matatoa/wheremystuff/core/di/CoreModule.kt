package com.matatoa.wheremystuff.core.di

import com.matatoa.wheremystuff.core.database.getPlaceDao
import com.matatoa.wheremystuff.core.database.getRoomDatabase
import com.matatoa.wheremystuff.core.datastore.MutablePlaceDataSource
import com.matatoa.wheremystuff.core.datastore.MutablePlaceDataSourceImpl
import com.matatoa.wheremystuff.core.datastore.PlaceDataStore
import com.matatoa.wheremystuff.core.datastore.PlaceDataStoreImpl
import com.matatoa.wheremystuff.core.repository.PlaceRepository
import com.matatoa.wheremystuff.core.repository.PlaceRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
    single { getRoomDatabase(builder = get()) }
    single { getPlaceDao(whereMyStuffDatabase = get()) }
}

val provideDataSourceModule = module {
    single<PlaceDataStore> {
        PlaceDataStoreImpl(placeDao = get())
    }
    single<MutablePlaceDataSource> {
        MutablePlaceDataSourceImpl(
            placeDao = get()
        )
    }
}

val provideRepositoryModule = module {
    single<PlaceRepository> {
        PlaceRepositoryImpl(placeDataSource = get(), mutablePlaceDataSource = get())
    }
}

/**
 * All Koin modules needed by the core layer
 */
val coreModules = listOf(
    provideDatabaseModule,
    provideDataSourceModule,
    provideRepositoryModule
)
