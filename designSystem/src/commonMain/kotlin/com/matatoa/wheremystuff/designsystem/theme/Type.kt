package com.matatoa.wheremystuff.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import wheremystuff.designsystem.generated.resources.Res
import wheremystuff.designsystem.generated.resources.inter_medium
import wheremystuff.designsystem.generated.resources.inter_regular
import wheremystuff.designsystem.generated.resources.inter_semibold

/** Builds the Inter font family with three bundled weights. */
@Composable
fun InterFontFamily() =
    FontFamily(
        Font(Res.font.inter_regular, weight = FontWeight.Normal),
        Font(Res.font.inter_medium, weight = FontWeight.Medium),
        Font(Res.font.inter_semibold, weight = FontWeight.SemiBold),
    )

/**
 * App-wide Material 3 typography scale using Inter.
 *
 * Access via `MaterialTheme.typography.<token>` inside any composable
 * wrapped with [WhereMyStuffTheme].
 */
@Composable
fun AppTypography(): Typography {
    val inter = InterFontFamily()
    return Typography(
        /** Screen titles — e.g. top-bar title, onboarding headline */
        headlineLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
        ),
        /** Dialog titles */
        headlineSmall = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        ),
        /** Card titles — e.g. adventure card name, section header */
        titleMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        ),
        /** Primary descriptions — e.g. card body text, instructions */
        bodyLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        /** Secondary info — e.g. timestamps, metadata, helper text */
        bodyMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        /** Button labels — e.g. dialog confirm / cancel actions */
        labelLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        /** Tab labels, chips, badges, captions */
        labelSmall = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
    )
}
