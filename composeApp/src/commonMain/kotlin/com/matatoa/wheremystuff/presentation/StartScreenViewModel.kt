package com.matatoa.wheremystuff.presentation

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.matatoa.wheremystuff.domain.model.PlacesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartScreenViewModel : ViewModel() {
    private val _state: MutableStateFlow<StartScreenState> =
        MutableStateFlow(value = StartScreenState())
    val state: StateFlow<StartScreenState> = _state.asStateFlow()
}

@Immutable
data class StartScreenState(
    val isLoading: Boolean = false,
    val places: List<PlacesData> = emptyList(),
)
