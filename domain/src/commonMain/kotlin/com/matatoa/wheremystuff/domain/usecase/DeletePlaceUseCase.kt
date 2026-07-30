package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.PlaceRepository

class DeletePlaceUseCase(
    val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(id: Int) =
        placeRepository.deletePlace(id = id)
}
