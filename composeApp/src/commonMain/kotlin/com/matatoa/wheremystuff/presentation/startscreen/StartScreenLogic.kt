package com.matatoa.wheremystuff.presentation.startscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.TablerIcons
import compose.icons.tablericons.Box
import compose.icons.tablericons.Building
import compose.icons.tablericons.BuildingSkyscraper
import compose.icons.tablericons.BuildingStore
import compose.icons.tablericons.Car
import compose.icons.tablericons.Heart
import compose.icons.tablericons.Home2
import compose.icons.tablericons.Star
import compose.icons.tablericons.Tractor
import compose.icons.tablericons.User

@Composable
fun placeIcon(iconName: String): ImageVector = when (iconName) {
    "Flat" -> TablerIcons.Building
    "House" -> TablerIcons.Home2
    "Balcony" -> TablerIcons.BuildingSkyscraper
    "Box" -> TablerIcons.Box
    "Tractor" -> TablerIcons.Tractor
    "Favorite" -> TablerIcons.Heart
    "Star" -> TablerIcons.Star
    "User" -> TablerIcons.User
    "Car" -> TablerIcons.Car
    "Store" -> TablerIcons.BuildingStore
    else -> TablerIcons.User
}
