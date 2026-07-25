package com.matatoa.wheremystuff.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<WhereMyStuffDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<WhereMyStuffDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}
