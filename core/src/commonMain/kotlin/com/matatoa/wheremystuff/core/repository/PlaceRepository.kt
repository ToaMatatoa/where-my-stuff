package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import com.matatoa.wheremystuff.core.datastore.MutablePlaceDataSource
import com.matatoa.wheremystuff.core.datastore.PlaceDataStore
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getAllPlaces(): Flow<List<PlaceEntity>>
    suspend fun addPlace(place: PlaceEntity)
}

class PlaceRepositoryImpl(
    val placeDataSource: PlaceDataStore,
    val mutablePlaceDataSource: MutablePlaceDataSource
) : PlaceRepository {
    override fun getAllPlaces(): Flow<List<PlaceEntity>> =
        placeDataSource.getAllPlaces()

    override suspend fun addPlace(place: PlaceEntity) =
        mutablePlaceDataSource.addPlace(place = place)
}
