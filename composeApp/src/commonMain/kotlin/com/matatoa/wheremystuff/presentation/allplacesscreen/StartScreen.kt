package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.EMPTY_STRING
import com.matatoa.wheremystuff.designsystem.Strings
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.domain.model.PlaceData

@Composable
fun StartScreen(
    state: StartScreenState,
    onOpenPlaceDetails: (Int) -> Unit,
    onSaveNewPlace: (String, String) -> Unit,
    onDeletePlace: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddPlaceDialog by rememberSaveable { mutableStateOf(value = false) }
    var placeName by rememberSaveable { mutableStateOf(value = EMPTY_STRING) }
    var placeIconName by rememberSaveable { mutableStateOf(value = EMPTY_STRING) }

    val isPlaceNameTaken = remember(placeName, state.places) {
        val trimmedName = placeName.trim()
        trimmedName.isNotEmpty() &&
                state.places.any { it.name.equals(other = trimmedName, ignoreCase = true) }
    }

    var placeIdToDelete by rememberSaveable { mutableStateOf<Int?>(value = null) }
    val placeNameToDelete = remember(placeIdToDelete, state.places) {
        state.places.firstOrNull { it.id == placeIdToDelete }?.name
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        TopBar(
            title = Strings.Common.TOP_BAR_TITLE,
            modifier = Modifier
                .padding(top = 48.dp),
        )

        StartScreenBase(
            state = state,
            onOpenPlaceDetailsClick = onOpenPlaceDetails,
            onDeletePlaceClick = { placeIdToDelete = it },
            onAddNewPlaceClick = { showAddPlaceDialog = true },
        )
    }

    ShowAddPlaceDialog(
        show = showAddPlaceDialog,
        placeName = placeName,
        placeIconName = placeIconName,
        isNameTaken = isPlaceNameTaken,
        onPlaceNameChange = { placeName = it },
        onPlaceIconNameChange = { placeIconName = it },
        onConfirm = {
            onSaveNewPlace(placeName.trim(), placeIconName)
            showAddPlaceDialog = false
            placeName = EMPTY_STRING
            placeIconName = EMPTY_STRING
        },
        onDismiss = {
            showAddPlaceDialog = false
            placeName = EMPTY_STRING
            placeIconName = EMPTY_STRING
        }
    )

    ShowDeletePlaceDialog(
        placeName = placeNameToDelete,
        onConfirm = {
            placeIdToDelete?.let(onDeletePlace)
            placeIdToDelete = null
        },
        onDismiss = { placeIdToDelete = null }
    )
}

@Composable
private fun StartScreenBase(
    state: StartScreenState,
    onOpenPlaceDetailsClick: (Int) -> Unit,
    onDeletePlaceClick: (Int) -> Unit,
    onAddNewPlaceClick: () -> Unit
) {
    when {
        state.isLoading -> StartScreenLoadingState()

        state.places.isEmpty() -> StartScreenEmptyState(
            onAddNewPlaceClick = onAddNewPlaceClick
        )

        else -> StartScreenCompletedState(
            places = state.places,
            onOpenPlaceDetailsClick = onOpenPlaceDetailsClick,
            onAddNewPlaceClick = onAddNewPlaceClick,
            onDeletePlaceClick = onDeletePlaceClick
        )
    }
}

@Composable
private fun StartScreenLoadingState(
    modifier: Modifier = Modifier,
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
private fun StartScreenEmptyState(
    onAddNewPlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 32.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = Strings.AllPlacesScreen.EMPTY_STATE_TEXT,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 40.dp),
        )

        FilledTonalButton(
            onClick = onAddNewPlaceClick,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 52.dp)
                .padding(horizontal = 20.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = Strings.AllPlacesScreen.ADD_PLACE,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun StartScreenCompletedState(
    places: List<PlaceData>,
    onOpenPlaceDetailsClick: (Int) -> Unit,
    onDeletePlaceClick: (Int) -> Unit,
    onAddNewPlaceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 16.dp),
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        items(
            items = places, key = { it.id }
        ) {
            StartScreenBaseCompletedListItem(
                name = it.name,
                iconName = it.iconName,
                onOpenPlaceDetailsClick = { onOpenPlaceDetailsClick(it.id) },
                onDeletePlaceClick = { onDeletePlaceClick(it.id) }
            )
        }

        item {
            FilledTonalButton(
                onClick = onAddNewPlaceClick,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 64.dp)
                    .padding(start = 20.dp, top = 24.dp, end = 20.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = Strings.AllPlacesScreen.ADD_PLACE,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun StartScreenBaseCompletedListItem(
    name: String,
    iconName: String,
    onOpenPlaceDetailsClick: () -> Unit,
    onDeletePlaceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(shape = RoundedCornerShape(size = 16.dp))
                .combinedClickable(
                    onClick = onOpenPlaceDetailsClick,
                    onLongClick = onDeletePlaceClick
                )
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            AnimatedVisibility(visible = iconName.isNotEmpty()) {
                Icon(
                    imageVector = PlaceIcon.iconFor(name = iconName),
                    contentDescription = Strings.Common.PLACE_ICON,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(size = 32.dp)
                )
            }

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    WhereMyStuffTheme {
        StartScreen(
            state = StartScreenState(
                places = listOf(
                    PlaceData(
                        name = "House",
                        iconName = "House"
                    ),
                    PlaceData(
                        id = 1,
                        name = "Bank",
                        iconName = ""
                    ),
                    PlaceData(
                        id = 3,
                        name = "House",
                        iconName = "House"
                    ),
                    PlaceData(
                        id = 4,
                        name = "Bank",
                        iconName = ""
                    )
                )
            ),
            onOpenPlaceDetails = {},
            onSaveNewPlace = { _, _ -> },
            onDeletePlace = {}
        )
    }
}
