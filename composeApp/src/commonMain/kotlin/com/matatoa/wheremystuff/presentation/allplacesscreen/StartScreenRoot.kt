package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matatoa.wheremystuff.domain.model.PlaceData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartScreenRoot(
    onOpenPlaceDetailsClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<StartScreenViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    StartScreen(
        state = state,
        onOpenPlaceDetails = onOpenPlaceDetailsClick,
        onSaveNewPlace = { placeName, placeIconName ->
            viewModel.addPlace(
                PlaceData(
                    name = placeName,
                    iconName = placeIconName
                )
            )
        },
        onDeletePlace = {
            viewModel.deletePlace(id = it)
        },
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}
