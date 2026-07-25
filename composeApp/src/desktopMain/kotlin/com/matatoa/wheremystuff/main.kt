package com.matatoa.wheremystuff

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.matatoa.wheremystuff.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "WhereMyStuff",
        ) {
            WhereMyStuff()
        }
    }
}