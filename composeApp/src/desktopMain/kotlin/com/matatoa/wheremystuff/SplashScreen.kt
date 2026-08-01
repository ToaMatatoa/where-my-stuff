package com.matatoa.wheremystuff

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.designsystem.theme.CanvasMint
import org.jetbrains.compose.resources.painterResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.splash_logo

/**
 * How long the splash stays up. Unlike Android and iOS - where the OS shows the splash for the
 * duration of real startup work - the desktop window only opens once the app is already ready,
 * so this duration is what makes the branding visible at all.
 */
const val SPLASH_DURATION_MS = 1200L

/** Desktop equivalent of the Android splash screen and the iOS launch screen. */
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(CanvasMint),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(Res.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )
    }
}
