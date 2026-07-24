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
}
