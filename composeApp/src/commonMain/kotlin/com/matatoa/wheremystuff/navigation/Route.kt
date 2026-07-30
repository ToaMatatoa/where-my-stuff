package com.matatoa.wheremystuff.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object StartScreen : Route

    @Serializable
    data class PlaceDetailsScreen(
        val id: Int,
    ) : Route
}
