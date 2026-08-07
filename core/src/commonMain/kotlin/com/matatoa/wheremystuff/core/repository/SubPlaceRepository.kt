package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import com.matatoa.wheremystuff.core.datastore.MutableSubPlaceDataSource
import com.matatoa.wheremystuff.core.datastore.SubPlaceDataStore
import kotlinx.coroutines.flow.Flow

interface SubPlaceRepository {
    fun getAllSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>>

    /** @return the id Room generated for the new sub-place. */
    suspend fun addSubPlace(subPlace: SubPlaceEntity): Long
    suspend fun updateSubPlaceDescription(id: Int, description: String)
    suspend fun deleteSubPlace(id: Int)
}

class SubPlaceRepositoryImpl(
    val subPlaceDataSource: SubPlaceDataStore,
    val mutableSubPlaceDataSource: MutableSubPlaceDataSource
) : SubPlaceRepository {
    override fun getAllSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>> =
        subPlaceDataSource.getAllSubPlaces(placeId = placeId)

    override suspend fun addSubPlace(subPlace: SubPlaceEntity): Long =
        mutableSubPlaceDataSource.addSubPlace(subPlace = subPlace)

    override suspend fun updateSubPlaceDescription(id: Int, description: String) =
        mutableSubPlaceDataSource.updateSubPlaceDescription(id = id, description = description)

    override suspend fun deleteSubPlace(id: Int) =
        mutableSubPlaceDataSource.deleteSubPlace(id = id)
}
