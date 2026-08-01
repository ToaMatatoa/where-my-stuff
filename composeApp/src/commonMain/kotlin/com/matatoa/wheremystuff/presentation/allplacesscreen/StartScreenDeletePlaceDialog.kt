package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.matatoa.wheremystuff.designsystem.Strings

@Composable
fun ShowDeletePlaceDialog(
    placeName: String?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (placeName == null) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = Strings.AllPlacesScreen.DELETE_PLACE_DIALOG_TITLE)
        },
        text = {
            Text(text = Strings.AllPlacesScreen.deletePlaceDialogText(placeName = placeName))
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error,
                ),
            ) {
                Text(text = Strings.Common.DELETE)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.Common.CANCEL)
            }
        },
    )
}
