package com.matatoa.wheremystuff.domain.usecase.stuff

import com.matatoa.wheremystuff.core.repository.StuffRepository

class DeleteStuffUseCase(
    val stuffRepository: StuffRepository
) {
    suspend operator fun invoke(id: Int) =
        stuffRepository.deleteStuff(id = id)
}