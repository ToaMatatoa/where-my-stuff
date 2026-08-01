package com.matatoa.wheremystuff.domain.di

import com.matatoa.wheremystuff.domain.usecase.place.AddPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.AddStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.AddSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.place.DeletePlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.DeleteStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.DeleteSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.place.GetAllPlacesUseCase
import com.matatoa.wheremystuff.domain.usecase.place.GetPlaceDetailsUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.GetStuffForPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.GetAllSubPlacesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(constructor = ::GetAllPlacesUseCase)
    factoryOf(constructor = ::GetPlaceDetailsUseCase)
    factoryOf(constructor = ::AddPlaceUseCase)
    factoryOf(constructor = ::DeletePlaceUseCase)

    factoryOf(constructor = ::GetAllSubPlacesUseCase)
    factoryOf(constructor = ::AddSubPlaceUseCase)
    factoryOf(constructor = ::DeleteSubPlaceUseCase)

    factoryOf(constructor = ::GetStuffForPlaceUseCase)
    factoryOf(constructor = ::AddStuffUseCase)
    factoryOf(constructor = ::DeleteStuffUseCase)
}
