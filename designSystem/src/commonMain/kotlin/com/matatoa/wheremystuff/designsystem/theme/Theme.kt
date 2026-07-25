package com.matatoa.wheremystuff.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * App-wide theme wrapper. Wrap screens in [WhereMyStuffTheme] instead of using [MaterialTheme] directly
 * so colors/typography stay centralized here.
 *
 * The app uses a single fixed color scheme; it intentionally does not follow the system light/dark setting.
 */
@Composable
fun WhereMyStuffTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography(),
        content = content,
    )
}
