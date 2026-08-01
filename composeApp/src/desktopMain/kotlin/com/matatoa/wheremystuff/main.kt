package com.matatoa.wheremystuff

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.matatoa.wheremystuff.di.initKoin
import org.jetbrains.compose.resources.painterResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.app_icon

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Where My Stuff",
            icon = painterResource(Res.drawable.app_icon),
        ) {
            WhereMyStuff()
        }
    }
}