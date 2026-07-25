package com.matatoa.wheremystuff.presentation.startscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.designsystem.Strings
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.domain.model.PlaceData

@Composable
fun StartScreen(
    state: StartScreenState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            title = Strings.Common.TOP_BAR_TITLE,
            modifier = Modifier
                .padding(top = 48.dp),
        )

        StartScreenBase(
            state = state
        )
    }
}

@Composable
private fun StartScreenBase(
    state: StartScreenState,
) {
    when {
        state.isLoading -> StartScreenBaseLoadingState()

        state.places.isEmpty() -> StartScreenBaseEmptyState(
            onAddNewPlaceClick = {}
        )

        else -> StartScreenBaseCompleted(
            places = state.places,
            onAddNewPlaceClick = {}
        )
    }
}

@Composable
private fun StartScreenBaseLoadingState(
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
private fun StartScreenBaseEmptyState(
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
            text = Strings.StartScreen.EMPTY_STATE_TEXT,
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
                    text = Strings.StartScreen.EMPTY_STATE_ADD_PLACE,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun StartScreenBaseCompleted(
    places: List<PlaceData>,
    onAddNewPlaceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        items(
            items = places, key = { it.id }
        ) {
            StartScreenBaseCompletedListItem(
                name = it.name,
                iconName = it.iconName
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
                    .padding(start = 20.dp, top = 16.dp, end = 20.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = Strings.StartScreen.EMPTY_STATE_ADD_PLACE,
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
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
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
        ) {
            AnimatedVisibility(visible = iconName.isNotEmpty()) {
                Icon(
                    imageVector = placeIcon(iconName = iconName),
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
                        id = 2,
                        name = "Flat",
                        iconName = "Flat"
                    ),
                    PlaceData(
                        id = 3,
                        name = "Balcony",
                        iconName = "Balcony"
                    ),
                    PlaceData(
                        id = 4,
                        name = "Big box",
                        iconName = "Box"
                    ),
                    PlaceData(
                        id = 5,
                        name = "Countryside House",
                        iconName = "Tractor"
                    )
                )
            )
        )
    }
}
