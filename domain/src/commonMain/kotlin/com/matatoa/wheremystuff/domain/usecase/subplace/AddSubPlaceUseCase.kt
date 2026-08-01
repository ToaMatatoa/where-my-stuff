package com.matatoa.wheremystuff.domain.usecase.subplace

import com.matatoa.wheremystuff.core.repository.SubPlaceRepository
import com.matatoa.wheremystuff.domain.mapper.toSubPlaceEntity
import com.matatoa.wheremystuff.domain.model.SubPlaceData

class AddSubPlaceUseCase(
    val subPlaceRepository: SubPlaceRepository
) {
    /** @return the id of the new sub-place, so callers can select it straight away. */
    suspend operator fun invoke(subPlace: SubPlaceData): Int =
        subPlaceRepository.addSubPlace(subPlace = subPlace.toSubPlaceEntity()).toInt()
}