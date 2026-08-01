package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.database.entity.StuffEntity
import com.matatoa.wheremystuff.core.datastore.MutableStuffDataSource
import com.matatoa.wheremystuff.core.datastore.StuffDataStore
import kotlinx.coroutines.flow.Flow

interface StuffRepository {
    fun getStuffForPlace(placeId: Int): Flow<List<StuffEntity>>

    suspend fun addStuff(stuff: StuffEntity)
    suspend fun deleteStuff(id: Int)
}

class StuffRepositoryImpl(
    val stuffDataSource: StuffDataStore,
    val mutableStuffDataSource: MutableStuffDataSource
) : StuffRepository {
    override fun getStuffForPlace(placeId: Int): Flow<List<StuffEntity>> =
        stuffDataSource.getStuffForPlace(placeId = placeId)

    override suspend fun addStuff(stuff: StuffEntity) =
        mutableStuffDataSource.addStuff(stuff = stuff)

    override suspend fun deleteStuff(id: Int) =
        mutableStuffDataSource.deleteStuff(id = id)
}
