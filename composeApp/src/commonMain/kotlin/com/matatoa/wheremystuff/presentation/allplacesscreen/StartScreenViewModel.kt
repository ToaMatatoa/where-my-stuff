package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matatoa.wheremystuff.STOP_TIME_OUT_MILLIS
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.domain.usecase.AddPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.GetAllPlacesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StartScreenViewModel(
    getAllPlacesUseCase: GetAllPlacesUseCase,
    private val addPlaceUseCase: AddPlaceUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<StartScreenState> =
        MutableStateFlow(value = StartScreenState())
    val state: StateFlow<StartScreenState> =
        combine(
            flow = getAllPlacesUseCase.invoke(),
            flow2 = _state
        ) { places, currentState ->
            currentState.copy(
                isLoading = false,
                places = places
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIME_OUT_MILLIS),
            initialValue = StartScreenState(isLoading = true)
        )

    fun addPlace(place: PlaceData) {
        viewModelScope.launch {
            addPlaceUseCase.invoke(
                place = place
            )
        }
    }
}

@Immutable
data class StartScreenState(
    val isLoading: Boolean = false,
    val places: List<PlaceData> = emptyList(),
)
