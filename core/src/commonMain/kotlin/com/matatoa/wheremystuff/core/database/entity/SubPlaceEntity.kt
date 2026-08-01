package com.matatoa.wheremystuff.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/** A named area inside a place, e.g. "Kitchen" inside a "Flat". */
@Entity(
    tableName = "sub_place",
    foreignKeys = [
        ForeignKey(
            entity = PlaceEntity::class,
            parentColumns = ["id"],
            childColumns = ["placeId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index(value = ["placeId"])],
)
data class SubPlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val placeId: Int,
    val name: String,
)
