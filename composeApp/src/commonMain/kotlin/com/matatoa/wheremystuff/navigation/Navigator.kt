package com.matatoa.wheremystuff.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(
    val state: NavigationState,
) {
    /** Pushes [route] on top of the stack, ignoring a repeated tap on the same destination. */
    fun navigate(route: NavKey) {
        if (state.backStack.lastOrNull() != route) {
            state.backStack.add(route)
        }
    }

    /** Pops the current destination. Does nothing on the start route: the stack may never be empty. */
    fun goBack() {
        if (state.canGoBack) {
            state.backStack.removeLastOrNull()
        }
    }
}
