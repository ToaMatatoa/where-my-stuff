package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.PlaceRepository
import com.matatoa.wheremystuff.domain.mapper.toPlaceEntity
import com.matatoa.wheremystuff.domain.model.PlaceData

class AddPlaceUseCase(
    val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(place: PlaceData) =
        placeRepository.addPlace(place = place.toPlaceEntity())
}
