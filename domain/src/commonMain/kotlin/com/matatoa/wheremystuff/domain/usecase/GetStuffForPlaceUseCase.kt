package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.StuffRepository
import com.matatoa.wheremystuff.domain.mapper.toStuffData
import com.matatoa.wheremystuff.domain.model.StuffData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStuffForPlaceUseCase(
    val stuffRepository: StuffRepository
) {
    operator fun invoke(placeId: Int): Flow<List<StuffData>> =
        stuffRepository.getStuffForPlace(placeId = placeId)
            .map { stuff -> stuff.map { it.toStuffData() } }
}
