package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.SubPlaceRepository

class DeleteSubPlaceUseCase(
    val subPlaceRepository: SubPlaceRepository
) {
    suspend operator fun invoke(id: Int) =
        subPlaceRepository.deleteSubPlace(id = id)
}
