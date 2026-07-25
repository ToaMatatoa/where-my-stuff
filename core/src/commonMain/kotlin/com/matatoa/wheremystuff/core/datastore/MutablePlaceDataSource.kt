package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.PlaceDao
import com.matatoa.wheremystuff.core.database.entity.PlaceEntity

interface MutablePlaceDataSource {
    suspend fun addPlace(place: PlaceEntity)
}

class MutablePlaceDataSourceImpl(
    val placeDao: PlaceDao
) : MutablePlaceDataSource {
    override suspend fun addPlace(place: PlaceEntity) =
        placeDao.addPlace(place = place)
}
