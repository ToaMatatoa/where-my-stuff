package com.matatoa.wheremystuff.core.di

import com.matatoa.wheremystuff.core.database.getPlaceDao
import com.matatoa.wheremystuff.core.database.getRoomDatabase
import com.matatoa.wheremystuff.core.database.getStuffDao
import com.matatoa.wheremystuff.core.database.getSubPlaceDao
import com.matatoa.wheremystuff.core.datastore.MutablePlaceDataSource
import com.matatoa.wheremystuff.core.datastore.MutablePlaceDataSourceImpl
import com.matatoa.wheremystuff.core.datastore.MutableStuffDataSource
import com.matatoa.wheremystuff.core.datastore.MutableStuffDataSourceImpl
import com.matatoa.wheremystuff.core.datastore.MutableSubPlaceDataSource
import com.matatoa.wheremystuff.core.datastore.MutableSubPlaceDataSourceImpl
import com.matatoa.wheremystuff.core.datastore.PlaceDataStore
import com.matatoa.wheremystuff.core.datastore.PlaceDataStoreImpl
import com.matatoa.wheremystuff.core.datastore.StuffDataStore
import com.matatoa.wheremystuff.core.datastore.StuffDataStoreImpl
import com.matatoa.wheremystuff.core.datastore.SubPlaceDataStore
import com.matatoa.wheremystuff.core.datastore.SubPlaceDataStoreImpl
import com.matatoa.wheremystuff.core.repository.PlaceRepository
import com.matatoa.wheremystuff.core.repository.PlaceRepositoryImpl
import com.matatoa.wheremystuff.core.repository.StuffRepository
import com.matatoa.wheremystuff.core.repository.StuffRepositoryImpl
import com.matatoa.wheremystuff.core.repository.SubPlaceRepository
import com.matatoa.wheremystuff.core.repository.SubPlaceRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
    single { getRoomDatabase(builder = get()) }
    single { getPlaceDao(whereMyStuffDatabase = get()) }
    single { getSubPlaceDao(whereMyStuffDatabase = get()) }
    single { getStuffDao(whereMyStuffDatabase = get()) }
}

val provideDataSourceModule = module {
    single<PlaceDataStore> {
        PlaceDataStoreImpl(placeDao = get())
    }
    single<MutablePlaceDataSource> {
        MutablePlaceDataSourceImpl(
            placeDao = get(),
        )
    }
    single<SubPlaceDataStore> {
        SubPlaceDataStoreImpl(subPlaceDao = get())
    }
    single<MutableSubPlaceDataSource> {
        MutableSubPlaceDataSourceImpl(
            subPlaceDao = get(),
        )
    }
    single<StuffDataStore> {
        StuffDataStoreImpl(stuffDao = get())
    }
    single<MutableStuffDataSource> {
        MutableStuffDataSourceImpl(
            stuffDao = get(),
        )
    }
}

val provideRepositoryModule = module {
    single<PlaceRepository> {
        PlaceRepositoryImpl(placeDataSource = get(), mutablePlaceDataSource = get())
    }
    single<SubPlaceRepository> {
        SubPlaceRepositoryImpl(subPlaceDataSource = get(), mutableSubPlaceDataSource = get())
    }
    single<StuffRepository> {
        StuffRepositoryImpl(stuffDataSource = get(), mutableStuffDataSource = get())
    }
}

/**
 * All Koin modules needed by the core layer
 */
val coreModules = listOf(
    provideDatabaseModule,
    provideDataSourceModule,
    provideRepositoryModule,
)
