package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.EMPTY_STRING
import com.matatoa.wheremystuff.NEW_PLACE_LENGTH
import com.matatoa.wheremystuff.designsystem.Strings
import kotlinx.coroutines.delay

@Composable
fun ShowAddPlaceDialog(
    show: Boolean,
    placeName: String,
    placeIconName: String,
    isNameTaken: Boolean,
    onPlaceNameChange: (String) -> Unit,
    onPlaceIconNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (!show) return

    val nameFocusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(timeMillis = 100L)
        nameFocusRequester.requestFocus()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = Strings.AllPlacesScreen.ADD_PLACE_DIALOG_TITLE)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
            ) {
                OutlinedTextField(
                    value = placeName,
                    onValueChange = {
                        if (it.length <= NEW_PLACE_LENGTH)
                            onPlaceNameChange(it)
                    },
                    singleLine = true,
                    isError = isNameTaken,
                    label = { Text(text = Strings.AllPlacesScreen.ADD_PLACE_NAME_LABEL) },
                    supportingText =
                        if (isNameTaken) {
                            { Text(text = Strings.AllPlacesScreen.ADD_PLACE_NAME_TAKEN) }
                        } else if (placeName.isNotEmpty()) {
                            {
                                Text(
                                    text = "${placeName.length}/$NEW_PLACE_LENGTH",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        } else {
                            null
                        },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester = nameFocusRequester),
                )

                Text(
                    text = Strings.AllPlacesScreen.ADD_PLACE_ICON_LABEL,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                AddPlaceIconGrid(
                    selectedIconName = placeIconName,
                    onIconClick = { iconName ->
                        onPlaceIconNameChange(if (placeIconName == iconName) EMPTY_STRING else iconName)
                    },
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = placeName.isNotBlank() && !isNameTaken,
            ) {
                Text(text = Strings.AllPlacesScreen.ADD_PLACE_CONFIRM)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.AllPlacesScreen.ADD_PLACE_CANCEL)
            }
        },
    )
}

@Composable
private fun AddPlaceIconGrid(
    selectedIconName: String,
    onIconClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        PlaceIcon.entries.chunked(size = 3).forEach { rowIcons ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
            ) {
                rowIcons.forEach { placeIcon ->
                    AddPlaceIconCell(
                        placeIcon = placeIcon,
                        selected = placeIcon.name == selectedIconName,
                        onClick = { onIconClick(placeIcon.name) },
                    )
                }
            }
        }
    }
}

@Composable
private fun AddPlaceIconCell(
    placeIcon: PlaceIcon,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(size = 12.dp)
    val containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
    else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size = 56.dp)
            .clip(shape = shape)
            .background(color = containerColor, shape = shape)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = shape,
            )
            .clickable(onClick = onClick),
    ) {
        Icon(
            imageVector = placeIcon.icon,
            contentDescription = placeIcon.name,
            tint = contentColor,
            modifier = Modifier
                .size(size = 28.dp),
        )
    }
}
