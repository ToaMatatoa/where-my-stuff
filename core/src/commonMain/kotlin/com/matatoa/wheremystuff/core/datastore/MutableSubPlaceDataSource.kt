package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.SubPlaceDao
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity

interface MutableSubPlaceDataSource {
    /** @return the id Room generated for the new sub-place. */
    suspend fun addSubPlace(subPlace: SubPlaceEntity): Long
    suspend fun deleteSubPlace(id: Int)
}

class MutableSubPlaceDataSourceImpl(
    val subPlaceDao: SubPlaceDao
) : MutableSubPlaceDataSource {
    override suspend fun addSubPlace(subPlace: SubPlaceEntity): Long =
        subPlaceDao.addSubPlace(subPlace = subPlace)

    override suspend fun deleteSubPlace(id: Int) =
        subPlaceDao.deleteSubPlace(id = id)
}
