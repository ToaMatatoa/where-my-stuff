package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import com.matatoa.wheremystuff.NEW_STUFF_LENGTH
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.common_add
import wheremystuff.composeapp.generated.resources.common_cancel
import wheremystuff.composeapp.generated.resources.place_details_add_stuff
import wheremystuff.composeapp.generated.resources.place_details_stuff_name_label

@Composable
fun ShowAddStuffDialog(
    show: Boolean,
    stuffName: String,
    onStuffNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (!show) return

    val nameFocusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(timeMillis = 100L)
        nameFocusRequester.requestFocus()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(Res.string.place_details_add_stuff))
        },
        text = {
            OutlinedTextField(
                value = stuffName,
                onValueChange = {
                    if (it.length <= NEW_STUFF_LENGTH)
                        onStuffNameChange(it)
                },
                singleLine = true,
                label = { Text(text = stringResource(Res.string.place_details_stuff_name_label)) },
                supportingText =
                    if (stuffName.isNotEmpty()) {
                        {
                            Text(
                                text = "${stuffName.length}/$NEW_STUFF_LENGTH",
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    } else {
                        null
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = nameFocusRequester),
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = stuffName.isNotBlank(),
            ) {
                Text(text = stringResource(Res.string.common_add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(Res.string.common_cancel))
            }
        },
    )
}
