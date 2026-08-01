package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.SubPlaceRepository
import com.matatoa.wheremystuff.domain.mapper.toSubPlaceData
import com.matatoa.wheremystuff.domain.model.SubPlaceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllSubPlacesUseCase(
    val subPlaceRepository: SubPlaceRepository
) {
    operator fun invoke(placeId: Int): Flow<List<SubPlaceData>> =
        subPlaceRepository.getAllSubPlaces(placeId = placeId)
            .map { subPlaces -> subPlaces.map { it.toSubPlaceData() } }
}
