package com.matatoa.wheremystuff.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** One item the user keeps in a sub-place. */
@Entity(
    tableName = "stuff",
    foreignKeys = [
        ForeignKey(
            entity = SubPlaceEntity::class,
            parentColumns = ["id"],
            childColumns = ["subPlaceId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["subPlaceId"])],
)
data class StuffEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subPlaceId: Int,
    val name: String,
)
