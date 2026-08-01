package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matatoa.wheremystuff.STOP_TIME_OUT_MILLIS
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.domain.model.StuffData
import com.matatoa.wheremystuff.domain.model.SubPlaceData
import com.matatoa.wheremystuff.domain.usecase.place.GetPlaceDetailsUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.AddStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.DeleteStuffUseCase
import com.matatoa.wheremystuff.domain.usecase.stuff.GetStuffForPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.AddSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.DeleteSubPlaceUseCase
import com.matatoa.wheremystuff.domain.usecase.subplace.GetAllSubPlacesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceDetailsScreenViewModel(
    private val placeId: Int,
    getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    getAllSubPlacesUseCase: GetAllSubPlacesUseCase,
    getStuffForPlaceUseCase: GetStuffForPlaceUseCase,
    private val addSubPlaceUseCase: AddSubPlaceUseCase,
    private val deleteSubPlaceUseCase: DeleteSubPlaceUseCase,
    private val addStuffUseCase: AddStuffUseCase,
    private val deleteStuffUseCase: DeleteStuffUseCase
) : ViewModel() {
    private val selectedSubPlaceId: MutableStateFlow<Int?> = MutableStateFlow(value = null)

    val state: StateFlow<PlaceScreenState> =
        combine(
            getPlaceDetailsUseCase.invoke(id = placeId),
            getAllSubPlacesUseCase.invoke(placeId = placeId),
            getStuffForPlaceUseCase.invoke(placeId = placeId),
            selectedSubPlaceId,
        ) { place, subPlaces, allStuff, selectedId ->
            val selection = selectedId?.takeIf { id -> subPlaces.any { it.id == id } }

            PlaceScreenState(
                isLoading = false,
                place = place,
                subPlaces = subPlaces,
                selectedSubPlaceId = selection,
                stuff = when (selection) {
                    null -> allStuff
                    else -> allStuff.filter { it.subPlaceId == selection }
                },
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIME_OUT_MILLIS),
            initialValue = PlaceScreenState(isLoading = true)
        )

    fun selectSubPlace(id: Int?) {
        selectedSubPlaceId.value = id
    }

    fun addSubPlace(name: String) = viewModelScope.launch {
        addSubPlaceUseCase.invoke(
            subPlace = SubPlaceData(
                placeId = placeId,
                name = name
            )
        )
    }

    fun deleteSubPlace(id: Int) = viewModelScope.launch {
        deleteSubPlaceUseCase.invoke(id = id)
    }

    fun addStuff(subPlaceId: Int, name: String) = viewModelScope.launch {
        addStuffUseCase.invoke(
            stuff = StuffData(
                subPlaceId = subPlaceId,
                name = name
            )
        )
    }

    fun deleteStuff(stuffId: Int) = viewModelScope.launch {
        deleteStuffUseCase.invoke(id = stuffId)
    }
}

@Immutable
data class PlaceScreenState(
    val isLoading: Boolean = false,
    val place: PlaceData? = null,
    val subPlaces: List<SubPlaceData> = emptyList(),
    /** `null` while the predefined "All" chip is selected. */
    val selectedSubPlaceId: Int? = null,
    /** Stuff of [selectedSubPlaceId], or of every sub-place when "All" is selected. */
    val stuff: List<StuffData> = emptyList(),
)
