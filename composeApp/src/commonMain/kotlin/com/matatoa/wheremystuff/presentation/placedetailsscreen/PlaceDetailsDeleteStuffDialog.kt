package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.common_cancel
import wheremystuff.composeapp.generated.resources.common_delete
import wheremystuff.composeapp.generated.resources.place_details_delete_stuff_dialog_text
import wheremystuff.composeapp.generated.resources.place_details_delete_stuff_dialog_title

@Composable
fun ShowDeleteStuffDialog(
    stuffName: String?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (stuffName == null) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(Res.string.place_details_delete_stuff_dialog_title))
        },
        text = {
            Text(
                text = stringResource(
                    Res.string.place_details_delete_stuff_dialog_text,
                    stuffName
                )
            )
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
