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
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import wheremystuff.composeapp.generated.resources.Res
import wheremystuff.composeapp.generated.resources.common_add
import wheremystuff.composeapp.generated.resources.common_cancel
import wheremystuff.composeapp.generated.resources.place_details_add_sub_place
import wheremystuff.composeapp.generated.resources.place_details_sub_place_name_label
import wheremystuff.composeapp.generated.resources.place_details_sub_place_name_taken

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
            Text(text = stringResource(Res.string.place_details_add_sub_place))
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
                label = { Text(text = stringResource(Res.string.place_details_sub_place_name_label)) },
                supportingText =
                    if (isNameTaken) {
                        { Text(text = stringResource(Res.string.place_details_sub_place_name_taken)) }
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
