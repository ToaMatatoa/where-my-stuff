package com.matatoa.wheremystuff.core.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DestructiveMigrationTest {
    @Test
    fun opensAVersion1DatabaseByRecreatingItDestructively() = runBlocking {
        val dbFile = File.createTempFile("wheremystuff-test", ".db").also { it.delete() }

        // Hand-build a database that looks like schema version 1.
        val connection = BundledSQLiteDriver().open(dbFile.absolutePath)
        try {
            connection.execSQL(
                "CREATE TABLE place (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT NOT NULL, iconName TEXT NOT NULL)",
            )
            connection.execSQL("INSERT INTO place (name, iconName) VALUES ('Flat', 'Flat')")
            connection.execSQL("PRAGMA user_version = 1")
        } finally {
            connection.close()
        }

        // Threw "A migration from 1 to 2 was required but not found" before the builder fix.
        val database = getRoomDatabase(
            builder = Room.databaseBuilder<WhereMyStuffDatabase>(name = dbFile.absolutePath),
        )

        try {
            // Destructive migration wipes the old rows and recreates every table.
            assertEquals(
                expected = emptyList(),
                actual = database.getPlaceDao().getAllPlaces().first(),
            )

            // The version 2 tables exist and the place -> sub_place relation works.
            database.getPlaceDao().addPlace(place = PlaceEntity(name = "Flat", iconName = "Flat"))
            val placeId = database.getPlaceDao().getAllPlaces().first().single().id
            database.getSubPlaceDao().addSubPlace(
                subPlace = SubPlaceEntity(placeId = placeId, name = "Kitchen"),
            )

            assertEquals(
                expected = listOf("Kitchen"),
                actual = database.getSubPlaceDao().getAllSubPlaces(placeId = placeId).first()
                    .map { it.name },
            )
        } finally {
            database.close()
            dbFile.delete()
        }
    }
}
