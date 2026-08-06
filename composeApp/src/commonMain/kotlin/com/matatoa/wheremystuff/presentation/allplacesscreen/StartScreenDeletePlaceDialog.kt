package com.matatoa.wheremystuff.presentation.allplacesscreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.all_places_delete_dialog_text
import wheremystuff.composeapp.generated.resources.all_places_delete_dialog_title
import wheremystuff.composeapp.generated.resources.common_cancel
import wheremystuff.composeapp.generated.resources.common_delete

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
            Text(text = stringResource(Res.string.all_places_delete_dialog_title))
        },
        text = {
            Text(text = stringResource(Res.string.all_places_delete_dialog_text, placeName))
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error,
                ),
            ) {
                Text(text = stringResource(Res.string.common_delete))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(Res.string.common_cancel))
            }
        },
    )
}
