package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlaceDetailsScreenRoot(
    placeId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<PlaceDetailsScreenViewModel> { parametersOf(placeId) }
    val state = viewModel.state.collectAsStateWithLifecycle().value

    PlaceDetailsScreen(
        state = state,
        onBackClick = onBackClick,
        onSelectSubPlace = {
            viewModel.selectSubPlace(id = it)
        },
        onAddSubPlaceDescriptionClick = {
            viewModel.startAddingSubPlaceDescription()
        },
        onSubPlaceDescriptionChange = {
            viewModel.changeSubPlaceDescription(description = it)
        },
        onSaveSubPlaceDescription = {
            viewModel.saveSubPlaceDescription()
        },
        onSaveNewSubPlace = {
            viewModel.addSubPlace(name = it)
        },
        onDeleteSubPlace = {
            viewModel.deleteSubPlace(id = it)
        },
        onSaveNewStuff = { subPlaceId, stuffName ->
            viewModel.addStuff(subPlaceId = subPlaceId, name = stuffName)
        },
        onDeleteStuff = {
            viewModel.deleteStuff(id = it)
        },
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}
