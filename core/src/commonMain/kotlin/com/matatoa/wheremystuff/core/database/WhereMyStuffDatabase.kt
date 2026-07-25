package com.matatoa.wheremystuff.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.matatoa.wheremystuff.core.database.dao.PlaceDao
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [PlaceEntity::class],
    version = 1
)
@ConstructedBy(value = AppDatabaseConstructor::class)
abstract class WhereMyStuffDatabase : RoomDatabase() {
    abstract fun getPlaceDao(): PlaceDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<WhereMyStuffDatabase> {
    override fun initialize(): WhereMyStuffDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<WhereMyStuffDatabase>): WhereMyStuffDatabase =
    builder
        .fallbackToDestructiveMigration(dropAllTables = true)
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

fun getPlaceDao(whereMyStuffDatabase: WhereMyStuffDatabase) = whereMyStuffDatabase.getPlaceDao()
