package com.matatoa.wheremystuff.designsystem

/**
 * Centralized string resources for the entire app.
 * Import [Strings] in any module that depends on :designsystem
 * and reference the constants instead of hardcoding text.
 *
 * Usage:  Text(text = Strings.Common.TopBar)
 */
object Strings {
    object Common {
        const val TOP_BAR_TITLE = "Where my Stuff"
        const val BACK = "Back"
        const val SEARCH = "Search"
        const val PLACE_ICON = "Place icon"
    }

    object AllPlacesScreen {
        const val EMPTY_STATE_TEXT =
            "You haven't added any places for your stuff. Please add at least one"
        const val EMPTY_STATE_ADD_PLACE = "Add place"

        const val ADD_PLACE_DIALOG_TITLE = "Add place"
        const val DELETE_PLACE_DIALOG_TITLE = "Do you want to delete this place?"
        const val ADD_PLACE_NAME_LABEL = "Place name"
        const val ADD_PLACE_NAME_TAKEN = "A place with this name already exists"
        const val ADD_PLACE_ICON_LABEL = "Choose an icon (optional)"
        const val ADD_PLACE_CONFIRM = "Add"
        const val DELETE_PLACE_CONFIRM = "Delete"
        const val ADD_PLACE_CANCEL = "Cancel"

        fun deletePlaceDialogText(placeName: String) =
            "\"$placeName\" will be deleted permanently."
    }

    object PlaceDetailsScreen {
        const val EMPTY_STATE_TEXT =
            "You have some problems with this place. Delete it and add again please!"
    }
}
