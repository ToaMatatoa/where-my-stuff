package com.matatoa.wheremystuff.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert
    suspend fun addPlace(place: PlaceEntity)

    @Query(value = "SELECT * FROM place ORDER BY id")
    fun getAllPlaces(): Flow<List<PlaceEntity>>

    /** Emits `null` once the place is deleted or if it never existed. */
    @Query(value = "SELECT * FROM place WHERE id = :id")
    fun getPlaceDetails(id: Int): Flow<PlaceEntity?>
}
