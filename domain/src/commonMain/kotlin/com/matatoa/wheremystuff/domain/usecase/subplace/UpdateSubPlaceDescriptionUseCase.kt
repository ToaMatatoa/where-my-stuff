package com.matatoa.wheremystuff.domain.usecase.subplace

import com.matatoa.wheremystuff.core.repository.SubPlaceRepository

class UpdateSubPlaceDescriptionUseCase(
    val subPlaceRepository: SubPlaceRepository
) {
    suspend operator fun invoke(id: Int, description: String) =
        subPlaceRepository.updateSubPlaceDescription(id = id, description = description)
}
