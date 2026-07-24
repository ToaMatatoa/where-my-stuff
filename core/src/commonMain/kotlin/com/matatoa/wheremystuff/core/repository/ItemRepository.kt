package com.matatoa.wheremystuff.core.repository

import com.matatoa.wheremystuff.core.model.Item

/**
 * Repository contract. Implemented by [ItemRepositoryImpl] and consumed by use cases in :domain.
 */
interface ItemRepository {
    suspend fun getItems(): List<Item>
}
