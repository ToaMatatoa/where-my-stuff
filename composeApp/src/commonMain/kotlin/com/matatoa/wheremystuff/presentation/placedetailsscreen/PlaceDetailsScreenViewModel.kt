package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matatoa.wheremystuff.EMPTY_STRING
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
import com.matatoa.wheremystuff.domain.usecase.subplace.UpdateSubPlaceDescriptionUseCase
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
    private val updateSubPlaceDescriptionUseCase: UpdateSubPlaceDescriptionUseCase,
    private val deleteSubPlaceUseCase: DeleteSubPlaceUseCase,
    private val addStuffUseCase: AddStuffUseCase,
    private val deleteStuffUseCase: DeleteStuffUseCase
) : ViewModel() {
    private val selectedSubPlaceId: MutableStateFlow<Int?> = MutableStateFlow(value = null)

    private val subPlaceDescriptionEdit: MutableStateFlow<SubPlaceDescriptionEdit> =
        MutableStateFlow(value = SubPlaceDescriptionEdit())

    val state: StateFlow<PlaceScreenState> =
        combine(
            getPlaceDetailsUseCase.invoke(id = placeId),
            getAllSubPlacesUseCase.invoke(placeId = placeId),
            getStuffForPlaceUseCase.invoke(placeId = placeId),
            selectedSubPlaceId,
            subPlaceDescriptionEdit,
        ) { place, subPlaces, allStuff, selectedId, descriptionEdit ->
            val savedDescription =
                subPlaces.firstOrNull { it.id == selectedId }?.description.orEmpty()
            val draft = descriptionEdit.draft

            PlaceScreenState(
                isLoading = false,
                place = place,
                subPlaces = subPlaces,
                selectedSubPlaceId = selectedId,
                stuff = when (selectedId) {
                    null -> allStuff
                    else -> allStuff.filter { it.subPlaceId == selectedId }
                },
                subPlaceDescription = draft ?: savedDescription,
                isSubPlaceDescriptionShown = savedDescription.isNotEmpty() ||
                        descriptionEdit.isAdding,
                isSubPlaceDescriptionChanged = draft != null && draft.trim() != savedDescription,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIME_OUT_MILLIS),
            initialValue = PlaceScreenState(isLoading = true)
        )

    fun selectSubPlace(id: Int?) {
        if (selectedSubPlaceId.value == id) return

        subPlaceDescriptionEdit.value = SubPlaceDescriptionEdit()
        selectedSubPlaceId.value = id
    }

    fun startAddingSubPlaceDescription() {
        subPlaceDescriptionEdit.value = subPlaceDescriptionEdit.value.copy(isAdding = true)
    }

    fun changeSubPlaceDescription(description: String) {
        subPlaceDescriptionEdit.value = subPlaceDescriptionEdit.value.copy(draft = description)
    }

    fun saveSubPlaceDescription() = viewModelScope.launch {
        val subPlaceId = selectedSubPlaceId.value ?: return@launch
        val description = subPlaceDescriptionEdit.value.draft?.trim() ?: return@launch

        // Neither half of the edit is cleared here, because both would snap back for as long
        // as the write takes: the draft to the previous description, and the "adding" session
        // to the "Add description" button. The stored value catching up settles both.
        subPlaceDescriptionEdit.value = subPlaceDescriptionEdit.value.copy(draft = description)
        updateSubPlaceDescriptionUseCase.invoke(id = subPlaceId, description = description)
    }

    fun addSubPlace(name: String) = viewModelScope.launch {
        val newSubPlaceId = addSubPlaceUseCase.invoke(
            subPlace = SubPlaceData(
                placeId = placeId,
                name = name
            )
        )

        selectSubPlace(id = newSubPlaceId)
    }

    fun deleteSubPlace(id: Int) = viewModelScope.launch {
        if (selectedSubPlaceId.value == id) selectSubPlace(id = null)

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

    fun deleteStuff(id: Int) = viewModelScope.launch {
        deleteStuffUseCase.invoke(id = id)
    }
}

@Immutable
data class PlaceScreenState(
    val isLoading: Boolean = false,
    val place: PlaceData? = null,
    val subPlaces: List<SubPlaceData> = emptyList(),
    /** `null` while the predefined "All" chip is selected. */
    val selectedSubPlaceId: Int? = null,
    val stuff: List<StuffData> = emptyList(),
    val subPlaceDescription: String = EMPTY_STRING,
    val isSubPlaceDescriptionShown: Boolean = false,
    val isSubPlaceDescriptionChanged: Boolean = false,
)

private data class SubPlaceDescriptionEdit(
    val draft: String? = null,
    val isAdding: Boolean = false,
)
