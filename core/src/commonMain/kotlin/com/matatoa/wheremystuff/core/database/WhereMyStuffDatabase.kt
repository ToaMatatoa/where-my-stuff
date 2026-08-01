package com.matatoa.wheremystuff.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.matatoa.wheremystuff.core.database.dao.PlaceDao
import com.matatoa.wheremystuff.core.database.dao.StuffDao
import com.matatoa.wheremystuff.core.database.dao.SubPlaceDao
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import com.matatoa.wheremystuff.core.database.entity.StuffEntity
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [PlaceEntity::class, SubPlaceEntity::class, StuffEntity::class],
    version = 2
)
@ConstructedBy(value = AppDatabaseConstructor::class)
abstract class WhereMyStuffDatabase : RoomDatabase() {
    abstract fun getPlaceDao(): PlaceDao
    abstract fun getSubPlaceDao(): SubPlaceDao
    abstract fun getStuffDao(): StuffDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<WhereMyStuffDatabase> {
    override fun initialize(): WhereMyStuffDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<WhereMyStuffDatabase>): WhereMyStuffDatabase =
    builder
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

fun getPlaceDao(whereMyStuffDatabase: WhereMyStuffDatabase) = whereMyStuffDatabase.getPlaceDao()

fun getSubPlaceDao(whereMyStuffDatabase: WhereMyStuffDatabase) =
    whereMyStuffDatabase.getSubPlaceDao()

fun getStuffDao(whereMyStuffDatabase: WhereMyStuffDatabase) = whereMyStuffDatabase.getStuffDao()
