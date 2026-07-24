package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.model.Item
import com.matatoa.wheremystuff.core.repository.ItemRepository

/**
 * Example use case. Use cases orchestrate repositories (from :core) and encapsulate business rules.
 */
class GetItemsUseCase(
    private val repository: ItemRepository,
) {
    suspend operator fun invoke(): List<Item> = repository.getItems()
}
