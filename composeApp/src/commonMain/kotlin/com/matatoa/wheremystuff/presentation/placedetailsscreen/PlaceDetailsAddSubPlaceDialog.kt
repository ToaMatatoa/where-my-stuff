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
import com.matatoa.wheremystuff.NEW_SUB_PLACE_LENGTH
import com.matatoa.wheremystuff.designsystem.Strings
import kotlinx.coroutines.delay

@Composable
fun ShowAddSubPlaceDialog(
    show: Boolean,
    subPlaceName: String,
    isNameTaken: Boolean,
    onSubPlaceNameChange: (String) -> Unit,
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
            Text(text = Strings.PlaceDetailsScreen.ADD_SUB_PLACE_DIALOG_TITLE)
        },
        text = {
            OutlinedTextField(
                value = subPlaceName,
                onValueChange = {
                    if (it.length <= NEW_SUB_PLACE_LENGTH)
                        onSubPlaceNameChange(it)
                },
                singleLine = true,
                isError = isNameTaken,
                label = { Text(text = Strings.PlaceDetailsScreen.ADD_SUB_PLACE_NAME_LABEL) },
                supportingText =
                    if (isNameTaken) {
                        { Text(text = Strings.PlaceDetailsScreen.ADD_SUB_PLACE_NAME_TAKEN) }
                    } else if (subPlaceName.isNotEmpty()) {
                        {
                            Text(
                                text = "${subPlaceName.length}/$NEW_SUB_PLACE_LENGTH",
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
                enabled = subPlaceName.isNotBlank() && !isNameTaken,
            ) {
                Text(text = Strings.Common.ADD)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.Common.CANCEL)
            }
        },
    )
}
