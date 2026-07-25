package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.PlaceDao
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import kotlinx.coroutines.flow.Flow

interface PlaceDataStore {
    fun getAllPlaces(): Flow<List<PlaceEntity>>
}

class PlaceDataStoreImpl(
    val placeDao: PlaceDao
) : PlaceDataStore {
    override fun getAllPlaces(): Flow<List<PlaceEntity>> =
        placeDao.getAllPlaces()
}
