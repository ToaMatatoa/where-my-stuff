package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.TablerIcons
import compose.icons.tablericons.Box
import compose.icons.tablericons.Building
import compose.icons.tablericons.BuildingBank
import compose.icons.tablericons.BuildingCottage
import compose.icons.tablericons.BuildingSkyscraper
import compose.icons.tablericons.BuildingStore
import compose.icons.tablericons.BuildingWarehouse
import compose.icons.tablericons.Car
import compose.icons.tablericons.Heart
import compose.icons.tablericons.Home2
import compose.icons.tablericons.Star
import compose.icons.tablericons.Tractor
import compose.icons.tablericons.User

/**
 * The place icons a user can choose from in the "add place" picker, in 4x3 grid order.
 *
 * Each entry's [name] is the stable identifier persisted with a place
 * (`PlaceData.iconName`); [icon] is the vector rendered in the UI. Build the picker
 * grid from [entries]. A place with no chosen icon or an unrecognized name renders
 * [FALLBACK] via [iconFor].
 */
enum class PlaceIcon(val icon: ImageVector) {
    Flat(icon = TablerIcons.Building),
    House(icon = TablerIcons.Home2),
    Balcony(icon = TablerIcons.BuildingSkyscraper),
    Box(icon = TablerIcons.Box),
    Tractor(icon = TablerIcons.Tractor),
    Favorite(icon = TablerIcons.Heart),
    Star(icon = TablerIcons.Star),
    Car(icon = TablerIcons.Car),
    Store(icon = TablerIcons.BuildingStore),
    Warehouse(icon = TablerIcons.BuildingWarehouse),
    Cottage(icon = TablerIcons.BuildingCottage),
    Bank(icon = TablerIcons.BuildingBank);

    companion object {
        /** Rendered for a place with no chosen icon or an unrecognized name. */
        val FALLBACK: ImageVector = TablerIcons.User

        fun fromNameOrNull(name: String): PlaceIcon? =
            entries.firstOrNull { it.name == name }

        fun iconFor(name: String): ImageVector =
            fromNameOrNull(name)?.icon ?: FALLBACK
    }
}
