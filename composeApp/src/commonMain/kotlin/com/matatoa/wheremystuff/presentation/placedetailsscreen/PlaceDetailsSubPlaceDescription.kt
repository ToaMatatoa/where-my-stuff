package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.SUB_PLACE_DESCRIPTION_LENGTH
import compose.icons.TablerIcons
import compose.icons.tablericons.DeviceFloppy
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.place_details_save_sub_place_description
import wheremystuff.composeapp.generated.resources.place_details_sub_place_description_label

/**
 * The description of the selected sub-place, editable in place.
 *
 * Shown for every sub-place but not for the predefined "All" chip, which is a read-only
 * overview rather than a sub-place of its own. The save button and the character counter
 * both appear only once the text differs from the stored description, so an untouched field
 * is just the description, full width and free of call to action.
 */
@Composable
fun PlaceDetailsSubPlaceDescription(
    description: String,
    isChanged: Boolean,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
    ) {
        OutlinedTextField(
            value = description,
            onValueChange = {
                if (it.length <= SUB_PLACE_DESCRIPTION_LENGTH) onDescriptionChange(it)
            },
            maxLines = 2,
            shape = RoundedCornerShape(size = 24.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = {
                Text(
                    text = stringResource(Res.string.place_details_sub_place_description_label),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            supportingText = if (isChanged) {
                {
                    Text(
                        text = "${description.length}/$SUB_PLACE_DESCRIPTION_LENGTH",
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            } else {
                null
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier
                .weight(weight = 1f),
        )

        SaveDescriptionButton(
            visible = isChanged,
            onClick = onSaveClick,
        )
    }
}

/**
 * The save action, which slides in only while there is an edit to store.
 */
@Composable
private fun SaveDescriptionButton(
    visible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(size = 56.dp)
                .clip(shape = RoundedCornerShape(size = 18.dp))
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(size = 18.dp),
                )
                .clickable(onClick = onClick),
        ) {
            Icon(
                imageVector = TablerIcons.DeviceFloppy,
                contentDescription = stringResource(
                    Res.string.place_details_save_sub_place_description
                ),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(size = 26.dp),
            )
        }
    }
}
