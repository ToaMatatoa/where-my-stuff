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
    }

    object StartScreen {
        const val EMPTY_STATE_TEXT = "You haven't added any places for your stuff. Please add at least one"
        const val EMPTY_STATE_ADD_PLACE = "Add place"
    }
}
