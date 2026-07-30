package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.PlaceRepository
import com.matatoa.wheremystuff.domain.mapper.toPlaceData
import com.matatoa.wheremystuff.domain.model.PlaceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDetailPlaceUseCase(
    val placeRepository: PlaceRepository
) {
    operator fun invoke(id: Int): Flow<PlaceData?> =
        placeRepository.getPlaceDetails(id = id)
            .map { place -> place?.toPlaceData() }

}
