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
        const val ADD = "Add"
        const val ALL = "All"
        const val CANCEL = "Cancel"
        const val DELETE = "Delete"
    }

    object AllPlacesScreen {
        const val EMPTY_STATE_TEXT =
            "You haven't added any places for your stuff. Please add at least one"
        const val ADD_PLACE = "Add place"
        const val DELETE_PLACE_DIALOG_TITLE = "Do you want to delete this place?"
        const val ADD_PLACE_NAME_LABEL = "Place name"
        const val ADD_PLACE_NAME_TAKEN = "A place with this name already exists"
        const val ADD_PLACE_ICON_LABEL = "Choose an icon (optional)"

        fun deletePlaceDialogText(placeName: String) =
            "\"$placeName\" will be deleted permanently."
    }

    object PlaceDetailsScreen {
        const val EMPTY_STATE_TEXT =
            "You have some problems with this place. Delete it and add again please!"

        /** The predefined chip that aggregates the stuff of every sub-place. */
        const val ADD_SUB_PLACE = "Add sub-place"

        const val ADD_SUB_PLACE_DIALOG_TITLE = "Add sub-place"
        const val ADD_SUB_PLACE_NAME_LABEL = "Sub-place name"
        const val ADD_SUB_PLACE_NAME_TAKEN = "A sub-place with this name already exists"
        const val DELETE_SUB_PLACE_DIALOG_TITLE = "Do you want to delete this sub-place?"

        fun deleteSubPlaceDialogText(subPlaceName: String) =
            "\"$subPlaceName\" and everything kept in it will be deleted permanently."

        const val ADD_STUFF_DIALOG_TITLE = "Add stuff to keep"
        const val ADD_STUFF_NAME_LABEL = "What do you keep here?"
        const val ADD_STUFF = "Add stuff to keep"

        const val NO_SUB_PLACES_TEXT =
            "Add your first sub-place, for example \"Kitchen\" or \"Balcony\""
        const val NO_STUFF_TEXT = "Nothing is kept here yet"
        const val NO_STUFF_AT_ALL_TEXT =
            "You haven't added any stuff to this place yet. Choose a sub-place above to add some"
    }
}
