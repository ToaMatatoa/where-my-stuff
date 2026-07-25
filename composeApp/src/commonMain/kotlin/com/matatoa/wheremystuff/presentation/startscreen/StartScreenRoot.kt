package com.matatoa.wheremystuff.presentation.startscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matatoa.wheremystuff.domain.model.PlaceData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<StartScreenViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    StartScreen(
        state = state,
        onSaveNewPlace = { placeName, placeIconName ->
            viewModel.addPlace(
                PlaceData(
                    name = placeName,
                    iconName = placeIconName
                )
            )
        },
        modifier = modifier
    )
}
