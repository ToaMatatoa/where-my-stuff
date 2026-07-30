package com.matatoa.wheremystuff.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.matatoa.wheremystuff.presentation.placescreen.PlaceScreenRoot
import com.matatoa.wheremystuff.presentation.startscreen.StartScreenRoot

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState(startRoute = Route.StartScreen)
    val navigator = remember(navigationState) { Navigator(state = navigationState) }

    NavDisplay(
        backStack = navigationState.backStack,
        onBack = navigator::goBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Route.StartScreen> {
                StartScreenRoot(
                    onPlaceClick = { placeId ->
                        navigator.navigate(route = Route.PlaceDetailsScreen(id = placeId))
                    },
                )
            }

            entry<Route.PlaceDetailsScreen> { route ->
                PlaceScreenRoot(
                    placeId = route.id,
                    onBackClick = navigator::goBack,
                )
            }
        },
        modifier = modifier
            .fillMaxSize(),
    )
}
