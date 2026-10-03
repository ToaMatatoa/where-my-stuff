package com.matatoa.wheremystuff.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

/**
 * Holds the single back stack the app navigates within: [startRoute] sits at the
 * bottom of it and detail routes are pushed on top.
 */
class NavigationState(
    val startRoute: NavKey,
    val backStack: NavBackStack<NavKey>,
) {
    /** False when [startRoute] is the only entry left, i.e. there is nothing to pop. */
    val canGoBack: Boolean
        get() = backStack.size > 1
}

@Composable
fun rememberNavigationState(startRoute: NavKey): NavigationState {
    val backStack = rememberNavBackStack(
        configuration = serializersConfig,
        startRoute,
    )

    return remember(startRoute, backStack) {
        NavigationState(
            startRoute = startRoute,
            backStack = backStack,
        )
    }
}

/**
 * Every [Route] must be registered here, otherwise the back stack cannot be restored
 * after process death.
 */
val serializersConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.StartScreen::class, Route.StartScreen.serializer())
            subclass(Route.PlaceDetailsScreen::class, Route.PlaceDetailsScreen.serializer())
        }
    }
}
