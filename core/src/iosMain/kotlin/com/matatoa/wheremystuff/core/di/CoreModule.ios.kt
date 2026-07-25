package com.matatoa.wheremystuff.core.di

import androidx.room.RoomDatabase
import com.matatoa.wheremystuff.core.database.WhereMyStuffDatabase
import com.matatoa.wheremystuff.core.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<WhereMyStuffDatabase>> {
        getDatabaseBuilder()
    }
}
