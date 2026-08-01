package com.matatoa.wheremystuff.di

import com.matatoa.wheremystuff.presentation.placedetailsscreen.PlaceDetailsScreenViewModel
import com.matatoa.wheremystuff.presentation.allplacesscreen.StartScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(constructor = ::StartScreenViewModel)

    // The place id comes from the navigation route, so it is passed in as a parameter.
    viewModel { params ->
        PlaceDetailsScreenViewModel(
            placeId = params.get(),
            getDetailPlaceUseCase = get(),
            getAllSubPlacesUseCase = get(),
            getStuffForPlaceUseCase = get(),
            addSubPlaceUseCase = get(),
            deleteSubPlaceUseCase = get(),
            addStuffUseCase = get(),
        )
    }
}
