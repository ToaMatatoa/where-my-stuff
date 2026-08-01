package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.StuffDao
import com.matatoa.wheremystuff.core.database.entity.StuffEntity
import kotlinx.coroutines.flow.Flow

interface StuffDataStore {
    fun getStuffForSubPlace(placeId: Int): Flow<List<StuffEntity>>
}

class StuffDataStoreImpl(
    val stuffDao: StuffDao
) : StuffDataStore {
    override fun getStuffForSubPlace(placeId: Int): Flow<List<StuffEntity>> =
        stuffDao.getStuffForSubPlace(placeId = placeId)
}
