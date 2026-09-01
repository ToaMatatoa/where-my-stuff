package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.SubPlaceDao
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import kotlinx.coroutines.flow.Flow

interface SubPlaceDataStore {
    fun getAllSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>>
}

class SubPlaceDataStoreImpl(
    val subPlaceDao: SubPlaceDao,
) : SubPlaceDataStore {
    override fun getAllSubPlaces(placeId: Int): Flow<List<SubPlaceEntity>> =
        subPlaceDao.getAllSubPlaces(placeId = placeId)
}
