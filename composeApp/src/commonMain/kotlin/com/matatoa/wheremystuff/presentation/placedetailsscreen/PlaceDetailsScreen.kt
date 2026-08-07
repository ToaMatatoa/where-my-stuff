package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.EMPTY_STRING
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.domain.model.StuffData
import com.matatoa.wheremystuff.domain.model.SubPlaceData
import com.matatoa.wheremystuff.presentation.allplacesscreen.PlaceIcon
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.place_details_empty_state
import wheremystuff.composeapp.generated.resources.place_details_no_sub_places

@Composable
fun PlaceDetailsScreen(
    state: PlaceScreenState,
    onBackClick: () -> Unit,
    onSelectSubPlace: (Int?) -> Unit,
    onAddSubPlaceDescriptionClick: () -> Unit,
    onSubPlaceDescriptionChange: (String) -> Unit,
    onSaveSubPlaceDescription: () -> Unit,
    onSaveNewSubPlace: (String) -> Unit,
    onDeleteSubPlace: (Int) -> Unit,
    onSaveNewStuff: (Int, String) -> Unit,
    onDeleteStuff: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddSubPlaceDialog by rememberSaveable { mutableStateOf(value = false) }
    var subPlaceName by rememberSaveable { mutableStateOf(value = EMPTY_STRING) }

    val isSubPlaceNameTaken = remember(subPlaceName, state.subPlaces) {
        val trimmedName = subPlaceName.trim()
        trimmedName.isNotEmpty() &&
                state.subPlaces.any { it.name.equals(other = trimmedName, ignoreCase = true) }
    }

    var subPlaceIdToDelete by rememberSaveable { mutableStateOf<Int?>(value = null) }
    val subPlaceNameToDelete = remember(subPlaceIdToDelete, state.subPlaces) {
        state.subPlaces.firstOrNull { it.id == subPlaceIdToDelete }?.name
    }

    var showAddStuffDialog by rememberSaveable { mutableStateOf(value = false) }
    var stuffName by rememberSaveable { mutableStateOf(value = EMPTY_STRING) }

    var stuffIdToDelete by rememberSaveable { mutableStateOf<Int?>(value = null) }
    val stuffNameToDelete = remember(stuffIdToDelete, state.stuff) {
        state.stuff.firstOrNull { it.id == stuffIdToDelete }?.name
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
    ) {
        TopBar(
            title = state.place?.name ?: EMPTY_STRING,
            titleIcon = state.place?.iconName?.let(block = PlaceIcon::fromNameOrNull)?.icon,
            showBackButton = true,
            onBackClick = onBackClick,
            modifier = Modifier
                .padding(top = 48.dp),
        )

        PlaceDetailsScreenBase(
            state = state,
            onSelectSubPlace = {
                focusManager.clearFocus()
                onSelectSubPlace(it)
            },
            onAddSubPlaceDescriptionClick = onAddSubPlaceDescriptionClick,
            onSubPlaceDescriptionChange = onSubPlaceDescriptionChange,
            onSaveSubPlaceDescription = {
                focusManager.clearFocus()
                onSaveSubPlaceDescription()
            },
            onAddSubPlaceClick = { showAddSubPlaceDialog = true },
            onDeleteSubPlaceClick = { subPlaceIdToDelete = it },
            onAddStuffClick = { showAddStuffDialog = true },
            onDeleteStuffClick = { stuffIdToDelete = it }
        )
    }

    ShowAddSubPlaceDialog(
        show = showAddSubPlaceDialog,
        subPlaceName = subPlaceName,
        isNameTaken = isSubPlaceNameTaken,
        onSubPlaceNameChange = { subPlaceName = it },
        onConfirm = {
            onSaveNewSubPlace(subPlaceName.trim())
            showAddSubPlaceDialog = false
            subPlaceName = EMPTY_STRING
        },
        onDismiss = {
            showAddSubPlaceDialog = false
            subPlaceName = EMPTY_STRING
        }
    )

    ShowDeleteSubPlaceDialog(
        subPlaceName = subPlaceNameToDelete,
        onConfirm = {
            subPlaceIdToDelete?.let(block = onDeleteSubPlace)
            subPlaceIdToDelete = null
        },
        onDismiss = { subPlaceIdToDelete = null }
    )

    ShowAddStuffDialog(
        // Dialog visibility survives process death but the selection does not, so pair them:
        // without this the dialog could reappear on "All" and silently add nothing.
        show = showAddStuffDialog && state.selectedSubPlaceId != null,
        stuffName = stuffName,
        onStuffNameChange = { stuffName = it },
        onConfirm = {
            state.selectedSubPlaceId?.let { onSaveNewStuff(it, stuffName.trim()) }
            showAddStuffDialog = false
            stuffName = EMPTY_STRING
        },
        onDismiss = {
            showAddStuffDialog = false
            stuffName = EMPTY_STRING
        }
    )

    ShowDeleteStuffDialog(
        stuffName = stuffNameToDelete,
        onConfirm = {
            stuffIdToDelete?.let { onDeleteStuff(it) }
            stuffIdToDelete = null
        },
        onDismiss = { stuffIdToDelete = null }
    )
}

@Composable
private fun PlaceDetailsScreenBase(
    state: PlaceScreenState,
    onSelectSubPlace: (Int?) -> Unit,
    onAddSubPlaceDescriptionClick: () -> Unit,
    onSubPlaceDescriptionChange: (String) -> Unit,
    onSaveSubPlaceDescription: () -> Unit,
    onAddSubPlaceClick: () -> Unit,
    onDeleteSubPlaceClick: (Int) -> Unit,
    onAddStuffClick: () -> Unit,
    onDeleteStuffClick: (Int) -> Unit
) {
    when {
        state.isLoading -> PlaceDetailsScreenLoadingState()

        state.place == null -> PlaceDetailsScreenEmptyState()

        else -> PlaceDetailsScreenCompletedState(
            state = state,
            onSelectSubPlace = onSelectSubPlace,
            onAddSubPlaceDescriptionClick = onAddSubPlaceDescriptionClick,
            onSubPlaceDescriptionChange = onSubPlaceDescriptionChange,
            onSaveSubPlaceDescription = onSaveSubPlaceDescription,
            onAddSubPlaceClick = onAddSubPlaceClick,
            onDeleteSubPlaceClick = onDeleteSubPlaceClick,
            onAddStuffClick = onAddStuffClick,
            onDeleteStuffClick = onDeleteStuffClick
        )
    }
}

@Composable
private fun PlaceDetailsScreenLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .size(size = 120.dp),
        )
    }
}

@Composable
private fun PlaceDetailsScreenEmptyState(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(Res.string.place_details_empty_state),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 40.dp),
        )
    }
}

@Composable
private fun PlaceDetailsScreenCompletedState(
    state: PlaceScreenState,
    onSelectSubPlace: (Int?) -> Unit,
    onAddSubPlaceDescriptionClick: () -> Unit,
    onSubPlaceDescriptionChange: (String) -> Unit,
    onSaveSubPlaceDescription: () -> Unit,
    onAddSubPlaceClick: () -> Unit,
    onDeleteSubPlaceClick: (Int) -> Unit,
    onAddStuffClick: () -> Unit,
    onDeleteStuffClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val subPlaceNameById = remember(state.subPlaces) {
        state.subPlaces.associate { it.id to it.name }
    }
    val isAllSelected = state.selectedSubPlaceId == null

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        PlaceDetailsSubPlacesRow(
            subPlaces = state.subPlaces,
            selectedSubPlaceId = state.selectedSubPlaceId,
            onSelectSubPlace = onSelectSubPlace,
            onDeleteSubPlace = onDeleteSubPlaceClick,
            onAddSubPlaceClick = onAddSubPlaceClick,
        )

        AnimatedVisibility(
            visible = !isAllSelected
        ) {
            PlaceDetailsSubPlaceDescription(
                description = state.subPlaceDescription,
                isShown = state.isSubPlaceDescriptionShown,
                isChanged = state.isSubPlaceDescriptionChanged,
                onAddClick = onAddSubPlaceDescriptionClick,
                onDescriptionChange = onSubPlaceDescriptionChange,
                onSaveClick = onSaveSubPlaceDescription,
            )
        }

        if (state.subPlaces.isEmpty()) {
            PlaceDetailsNoSubPlacesState()
            return@Column
        }

        PlaceDetailsStuffList(
            stuff = state.stuff,
            subPlaceNameById = subPlaceNameById,
            isAllSelected = isAllSelected,
            // Stuff always belongs to one sub-place, so "All" is a read-only overview.
            onAddStuffClick = if (isAllSelected) null else onAddStuffClick,
            onDeleteStuffClick = onDeleteStuffClick
        )
    }
}

@Composable
private fun PlaceDetailsNoSubPlacesState(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(Res.string.place_details_no_sub_places),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 40.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceDetailsScreenPreview() {
    WhereMyStuffTheme {
        PlaceDetailsScreen(
            state = PlaceScreenState(
                place = PlaceData(
                    id = 1,
                    name = "House",
                    iconName = "House"
                ),
                subPlaces = listOf(
                    SubPlaceData(id = 1, placeId = 1, name = "1st room"),
                    SubPlaceData(
                        id = 2,
                        placeId = 1,
                        name = "Kitchen",
                        description = "Cupboard above the fridge"
                    ),
                    SubPlaceData(id = 3, placeId = 1, name = "Toilet"),
                    SubPlaceData(id = 4, placeId = 1, name = "Balcony"),
                ),
                selectedSubPlaceId = 2,
                stuff = listOf(
                    StuffData(id = 2, subPlaceId = 2, name = "Coffee grinder"),
                ),
                subPlaceDescription = "Cupboard above the fridge",
                isSubPlaceDescriptionShown = true,
            ),
            onBackClick = {},
            onSelectSubPlace = {},
            onAddSubPlaceDescriptionClick = {},
            onSubPlaceDescriptionChange = {},
            onSaveSubPlaceDescription = {},
            onSaveNewSubPlace = {},
            onDeleteSubPlace = {},
            onSaveNewStuff = { _, _ -> },
            onDeleteStuff = {}
        )
    }
}
