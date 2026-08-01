package com.matatoa.wheremystuff

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.matatoa.wheremystuff.di.initKoin
import kotlinx.coroutines.delay
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
            var showSplash by remember { mutableStateOf(true) }
            LaunchedEffect(Unit) {
                delay(SPLASH_DURATION_MS)
                showSplash = false
            }

            if (showSplash) {
                SplashScreen()
            } else {
                WhereMyStuff()
            }
        }
    }
}