package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.model.Item

/**
 * In-memory placeholder implementation of [ItemRepository].
 * Back this with Room (a DAO under com.matatoa.wheremystuff.core.db) once the database is set up.
 */
class ItemRepositoryImpl : ItemRepository {
    override suspend fun getItems(): List<Item> = emptyList()
}
