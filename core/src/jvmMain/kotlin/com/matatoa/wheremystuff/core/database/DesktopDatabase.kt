package com.matatoa.wheremystuff.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<WhereMyStuffDatabase> {
    val dbFile = File(System.getProperty("user.home"), "my_room.db")
    return Room.databaseBuilder<WhereMyStuffDatabase>(
        name = dbFile.absolutePath,
    )
}
