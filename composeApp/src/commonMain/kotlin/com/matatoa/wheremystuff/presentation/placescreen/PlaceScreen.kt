package com.matatoa.wheremystuff.presentation.placescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.EMPTY_STRING
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.presentation.startscreen.PlaceIcon

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

        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
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
                )
            ),
            onBackClick = {}
        )
    }
}
