package com.matatoa.wheremystuff.domain.di

import com.matatoa.wheremystuff.domain.usecase.AddPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.AddStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.AddSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.DeletePlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.DeleteStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.DeleteSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.GetAllPlacesUseCase
import com.matatoa.wheremystuff.domain.usecase.GetDetailPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.GetStuffForPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.GetAllSubPlacesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(constructor = ::GetAllPlacesUseCase)
    factoryOf(constructor = ::GetDetailPlaceUseCase)
    factoryOf(constructor = ::AddPlaceUseCase)
    factoryOf(constructor = ::DeletePlaceUseCase)

    factoryOf(constructor = ::GetAllSubPlacesUseCase)
    factoryOf(constructor = ::AddSubPlaceUseCase)
    factoryOf(constructor = ::DeleteSubPlaceUseCase)

    factoryOf(constructor = ::GetStuffForPlaceUseCase)
    factoryOf(constructor = ::AddStuffUseCase)
    factoryOf(constructor = ::DeleteStuffUseCase)
}
