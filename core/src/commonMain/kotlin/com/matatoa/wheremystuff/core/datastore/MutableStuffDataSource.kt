package com.matatoa.wheremystuff.core.datastore

import com.matatoa.wheremystuff.core.database.dao.StuffDao
import com.matatoa.wheremystuff.core.database.entity.StuffEntity

interface MutableStuffDataSource {
    suspend fun addStuff(stuff: StuffEntity)
    suspend fun deleteStuff(id: Int)
}

class MutableStuffDataSourceImpl(
    val stuffDao: StuffDao
) : MutableStuffDataSource {
    override suspend fun addStuff(stuff: StuffEntity) =
        stuffDao.addStuff(stuff = stuff)

    override suspend fun deleteStuff(id: Int) =
        stuffDao.deleteStuff(id = id)
}
