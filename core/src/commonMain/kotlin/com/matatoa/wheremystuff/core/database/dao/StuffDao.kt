package com.matatoa.wheremystuff.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matatoa.wheremystuff.core.database.entity.StuffEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StuffDao {
    @Insert
    suspend fun addStuff(stuff: StuffEntity)

    /**
     * Every item kept in any sub-place of [placeId].
     *
     * The screen filters this by the selected sub-place, so a single query backs both
     * the per-sub-place lists and the "All" list. Ordering by sub-place first groups the
     * "All" list under its sub-place labels; a filtered list only ever holds one
     * sub-place, so there it still reads as insertion order.
     */
    @Query(
        value = """
            SELECT stuff.* FROM stuff
            INNER JOIN sub_place ON stuff.subPlaceId = sub_place.id
            WHERE sub_place.placeId = :placeId
            ORDER BY sub_place.id, stuff.id
        """,
    )
    fun getStuffForPlace(placeId: Int): Flow<List<StuffEntity>>

    @Query(value = "DELETE FROM stuff WHERE id = :id")
    suspend fun deleteStuff(id: Int)
}
