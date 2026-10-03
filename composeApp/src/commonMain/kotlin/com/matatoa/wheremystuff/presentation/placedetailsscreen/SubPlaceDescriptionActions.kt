package com.matatoa.wheremystuff.presentation.placedetailsscreen

/**
 * What the user can do with a sub-place's description, grouped so the screens passing it
 * down do not each spend three parameters on it.
 */
data class SubPlaceDescriptionActions(
    val onAddClick: () -> Unit,
    val onChange: (String) -> Unit,
    val onSave: () -> Unit,
)
