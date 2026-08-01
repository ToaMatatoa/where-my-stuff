package com.matatoa.wheremystuff.designsystem.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ──────────────────────────────────────────────
// Brand palette — the raw "Where My Stuff" greens.
// These are the source-of-truth swatches; the semantic
// roles below reference them so nothing is hard-coded twice.
// ──────────────────────────────────────────────

/** Near-white mint — the app canvas. */
val CanvasMint = Color(0xFFF6FFF8)

/** Very pale cyan-mint — raised cards / list items. */
val MistPale = Color(0xFFEAF4F4)

/** Pale mint — chips, dividers, selected rows. */
val MintPale = Color(0xFFCCE3DE)

/** Medium sage — soft button containers, secondary accent. */
val SageMuted = Color(0xFFA4C3B2)

/** Deep sage — primary accent: buttons, FAB, list section titles. */
val SageDeep = Color(0xFF6B9080)

// Added supporting tones (same green family, for text & states)

/** Soft near-black green — titles & primary text. Reads as black, feels calmer. */
val InkGreen = Color(0xFF1E2B25)

/** Muted slate-green — secondary text, captions, item counts. */
val SlateSage = Color(0xFF4E655B)

/** Sage-tinted hairline — borders, dividers, text-field outlines. */
val SageOutline = Color(0xFFB2CCC1)

/** Complementary terracotta — destructive actions / errors. */
val Terracotta = Color(0xFFB4503B)

// ──────────────────────────────────────────────
// Semantic roles — one scheme, used for both light and dark system modes.
// ──────────────────────────────────────────────

/** Main screen background (and top bar, which blends into it). */
val Background = CanvasMint

/** Titles, top-bar text, and body text on the background. */
val OnBackground = InkGreen

/** Cards, bottom sheets, dialogs, list item rows. */
val Surface = MistPale

/** Primary text inside cards / list items. */
val OnSurface = InkGreen

/**
 * The elevated-container ramp Material 3 reaches for on its own — dialogs, menus,
 * bottom sheets. Left unset these fall back to Material's baseline lavender neutrals,
 * so they are pinned to the brand tones here even though no code names them directly.
 */
val SurfaceContainerLowest = CanvasMint
val SurfaceContainerLow = MistPale
val SurfaceContainer = MistPale
val SurfaceContainerHigh = MistPale
val SurfaceContainerHighest = MintPale

/** Inactive chips, dividers, selected/subtle containers. */
val SurfaceVariant = MintPale

/** Secondary text on surfaces (captions, item counts). */
val OnSurfaceVariant = SlateSage

/** Accent buttons, FAB, list section titles, selected tab icon. */
val Primary = SageDeep

/** Text / icon on top of primary-colored surfaces (e.g. FAB icon). */
val OnPrimary = CanvasMint

/** Soft highlight behind icons, selected chip / secondary button background. */
val PrimaryContainer = SageMuted

/** Text / icon on top of the soft primary container. */
val OnPrimaryContainer = InkGreen

/** Borders, thin dividers, text-field outlines. */
val Outline = SageOutline

/** The softer divider tone Material reaches for by default (e.g. HorizontalDivider). */
val OutlineVariant = MintPale

/** Destructive actions (delete a place / item). */
val Error = Terracotta

/** Text / icon on top of error-colored surfaces. */
val OnError = CanvasMint

// ──────────────────────────────────────────────
// The single Material 3 color scheme consumed by WhereMyStuffTheme.
// ──────────────────────────────────────────────

val AppColorScheme = lightColorScheme(
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    outline = Outline,
    outlineVariant = OutlineVariant,
    error = Error,
    onError = OnError,
)
