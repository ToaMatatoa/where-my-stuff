package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.MAX_SUB_PLACES
import com.matatoa.wheremystuff.designsystem.Strings
import com.matatoa.wheremystuff.domain.model.SubPlaceData
import compose.icons.TablerIcons
import compose.icons.tablericons.Plus

/**
 * The sub-places of the open place, as a wrapping row of chips.
 *
 * The first chip is the predefined "All" one, which shows the stuff of every sub-place and
 * cannot be deleted. Long-pressing any other chip asks to delete that sub-place. The trailing
 * "+" chip disappears once [MAX_SUB_PLACES] sub-places exist.
 */
@Composable
fun PlaceDetailsSubPlacesRow(
    subPlaces: List<SubPlaceData>,
    selectedSubPlaceId: Int?,
    onSelectSubPlace: (Int?) -> Unit,
    onDeleteSubPlace: (Int) -> Unit,
    onAddSubPlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        SubPlaceChip(
            name = Strings.Common.ALL,
            selected = selectedSubPlaceId == null,
            onClick = { onSelectSubPlace(null) },
            onLongClick = null,
        )

        subPlaces.forEach { subPlace ->
            SubPlaceChip(
                name = subPlace.name,
                selected = subPlace.id == selectedSubPlaceId,
                onClick = { onSelectSubPlace(subPlace.id) },
                onLongClick = { onDeleteSubPlace(subPlace.id) },
            )
        }

        if (subPlaces.size < MAX_SUB_PLACES) {
            AddSubPlaceChip(onClick = onAddSubPlaceClick)
        }
    }
}

@Composable
private fun SubPlaceChip(
    name: String,
    selected: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(size = 16.dp)
    val containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
    else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape = shape)
            .background(color = containerColor, shape = shape)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = shape,
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun AddSubPlaceChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(size = 16.dp)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape = shape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = shape,
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Icon(
            imageVector = TablerIcons.Plus,
            contentDescription = Strings.PlaceDetailsScreen.ADD_SUB_PLACE,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(size = 20.dp),
        )
    }
}
