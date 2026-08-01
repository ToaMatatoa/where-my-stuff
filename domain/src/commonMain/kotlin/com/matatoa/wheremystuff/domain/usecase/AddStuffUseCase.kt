package com.matatoa.wheremystuff.domain.usecase

import com.matatoa.wheremystuff.core.repository.StuffRepository
import com.matatoa.wheremystuff.domain.mapper.toStuffEntity
import com.matatoa.wheremystuff.domain.model.StuffData

class AddStuffUseCase(
    val stuffRepository: StuffRepository
) {
    suspend operator fun invoke(stuff: StuffData) =
        stuffRepository.addStuff(stuff = stuff.toStuffEntity())
}
