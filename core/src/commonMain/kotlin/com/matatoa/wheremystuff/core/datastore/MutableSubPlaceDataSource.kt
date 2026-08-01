package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.SubPlaceDao
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity

interface MutableSubPlaceDataSource {
    suspend fun addSubPlace(subPlace: SubPlaceEntity)
    suspend fun deleteSubPlace(id: Int)
}

class MutableSubPlaceDataSourceImpl(
    val subPlaceDao: SubPlaceDao
) : MutableSubPlaceDataSource {
    override suspend fun addSubPlace(subPlace: SubPlaceEntity) =
        subPlaceDao.addSubPlace(subPlace = subPlace)

    override suspend fun deleteSubPlace(id: Int) =
        subPlaceDao.deleteSubPlace(id = id)
}
