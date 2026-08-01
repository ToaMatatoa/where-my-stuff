package com.matatoa.wheremystuff.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubPlaceDao {
    /** @return the id Room generated for the new row. */
    @Insert
    suspend fun addSubPlace(subPlace: SubPlaceEntity): Long

    @Query(value = "SELECT * FROM sub_place WHERE placeId = :placeId ORDER BY id")
    fun getAllSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>>

    /** Cascades to every piece of stuff kept in this sub-place. */
    @Query(value = "DELETE FROM sub_place WHERE id = :id")
    suspend fun deleteSubPlace(id: Int)
}
