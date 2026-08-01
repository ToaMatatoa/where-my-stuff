package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import com.matatoa.wheremystuff.core.datastore.MutableSubPlaceDataSource
import com.matatoa.wheremystuff.core.datastore.SubPlaceDataStore
import kotlinx.coroutines.flow.Flow

interface SubPlaceRepository {
    fun getSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>>

    suspend fun addSubPlace(subPlace: SubPlaceEntity)
    suspend fun deleteSubPlace(id: Int)
}

class SubPlaceRepositoryImpl(
    val subPlaceDataSource: SubPlaceDataStore,
    val mutableSubPlaceDataSource: MutableSubPlaceDataSource
) : SubPlaceRepository {
    override fun getSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>> =
        subPlaceDataSource.getSubPlaces(placeId = placeId)

    override suspend fun addSubPlace(subPlace: SubPlaceEntity) =
        mutableSubPlaceDataSource.addSubPlace(subPlace = subPlace)

    override suspend fun deleteSubPlace(id: Int) =
        mutableSubPlaceDataSource.deleteSubPlace(id = id)
}
