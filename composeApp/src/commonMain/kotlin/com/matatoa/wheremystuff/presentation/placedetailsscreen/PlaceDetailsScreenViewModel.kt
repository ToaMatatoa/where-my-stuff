package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matatoa.wheremystuff.STOP_TIME_OUT_MILLIS
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.domain.usecase.GetDetailPlaceUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PlaceDetailsScreenViewModel(
    placeId: Int,
    getDetailPlaceUseCase: GetDetailPlaceUseCase,
) : ViewModel() {
    val state: StateFlow<PlaceScreenState> =
        getDetailPlaceUseCase.invoke(id = placeId)
            .map { place ->
                PlaceScreenState(
                    isLoading = false,
                    place = place,
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIME_OUT_MILLIS),
                initialValue = PlaceScreenState(isLoading = true)
            )
}

@Immutable
data class PlaceScreenState(
    val isLoading: Boolean = false,
    val place: PlaceData? = null,
)
