package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.SubPlaceRepository
import com.matatoa.wheremystuff.domain.mapper.toSubPlaceEntity
import com.matatoa.wheremystuff.domain.model.SubPlaceData

class AddSubPlaceUseCase(
    val subPlaceRepository: SubPlaceRepository
) {
    suspend operator fun invoke(subPlace: SubPlaceData) =
        subPlaceRepository.addSubPlace(subPlace = subPlace.toSubPlaceEntity())
}
