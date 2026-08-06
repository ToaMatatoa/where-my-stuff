package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.designsystem.PrimaryActionButton
import com.matatoa.wheremystuff.domain.model.StuffData
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.place_details_add_stuff
import wheremystuff.composeapp.generated.resources.place_details_no_stuff
import wheremystuff.composeapp.generated.resources.place_details_no_stuff_at_all

/**
 * The stuff kept in the selected sub-place.
 *
 * While the predefined "All" chip is selected this shows the stuff of every sub-place, each
 * item labelled with the sub-place it lives in, and no add button — stuff is always added to
 * one specific sub-place.
 */
@Composable
fun PlaceDetailsStuffList(
    stuff: List<StuffData>,
    subPlaceNameById: Map<Int, String>,
    isAllSelected: Boolean,
    onAddStuffClick: (() -> Unit)?,
    onDeleteStuffClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (stuff.isEmpty()) {
        PlaceDetailsStuffEmptyState(
            text = if (isAllSelected) stringResource(Res.string.place_details_no_stuff_at_all)
            else stringResource(Res.string.place_details_no_stuff),
            onAddStuffClick = onAddStuffClick,
            modifier = modifier,
        )
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(top = 8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
            .navigationBarsPadding(),
    ) {
        items(
            items = stuff, key = { it.id }
        ) {
            PlaceDetailsStuffListItem(
                name = it.name,
                // Only the "All" list mixes sub-places, so only it needs the label.
                subPlaceName = if (isAllSelected) subPlaceNameById[it.subPlaceId] else null,
                onDeleteStuffClick = { onDeleteStuffClick(it.id) }
            )
        }

        if (onAddStuffClick != null) {
            item {
                PrimaryActionButton(
                    text = stringResource(Res.string.place_details_add_stuff),
                    onClick = onAddStuffClick,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun PlaceDetailsStuffEmptyState(
    text: String,
    onAddStuffClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 32.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 40.dp),
        )

        if (onAddStuffClick != null) {
            PrimaryActionButton(
                text = stringResource(Res.string.place_details_add_stuff),
                onClick = onAddStuffClick,
            )
        }
    }
}

@Composable
private fun PlaceDetailsStuffListItem(
    name: String,
    subPlaceName: String?,
    onDeleteStuffClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                .padding(horizontal = 44.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(space = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    indication = null,
                    interactionSource = null,
                    onClick = {},
                    onLongClick = onDeleteStuffClick
                )
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            if (subPlaceName != null) {
                Text(
                    text = subPlaceName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
