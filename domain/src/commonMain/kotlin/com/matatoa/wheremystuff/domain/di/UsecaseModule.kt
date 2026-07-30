package com.matatoa.wheremystuff.domain.di

import com.matatoa.wheremystuff.domain.usecase.AddPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.GetAllPlacesUseCase
import com.matatoa.wheremystuff.domain.usecase.GetDetailPlaceUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(constructor = ::GetAllPlacesUseCase)
    factoryOf(constructor = ::GetDetailPlaceUseCase)
    factoryOf(constructor = ::AddPlaceUseCase)
}
