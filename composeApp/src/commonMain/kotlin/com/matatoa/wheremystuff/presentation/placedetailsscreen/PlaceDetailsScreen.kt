package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.EMPTY_STRING
import com.matatoa.wheremystuff.designsystem.Strings
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.presentation.allplacesscreen.PlaceIcon

@Composable
fun PlaceDetailsScreen(
    state: PlaceScreenState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        TopBar(
            title = state.place?.name ?: EMPTY_STRING,
            titleIcon = state.place?.iconName?.let(PlaceIcon::fromNameOrNull)?.icon,
            showBackButton = true,
            onBackClick = onBackClick,
            modifier = Modifier
                .padding(top = 48.dp),
        )

        PlaceDetailsScreenBase(
            state = state
        )
    }
}

@Composable
private fun PlaceDetailsScreenBase(
    state: PlaceScreenState
) {
    when {
        state.isLoading -> PlaceDetailsScreenLoadingState()
        state.place == null -> PlaceDetailsScreenEmptyState()
        else -> PlaceDetailsScreenCompletedState()
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
            text = Strings.PlaceDetailsScreen.EMPTY_STATE_TEXT,
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
    modifier: Modifier = Modifier
) {

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
                )
            ),
            onBackClick = {}
        )
    }
}
