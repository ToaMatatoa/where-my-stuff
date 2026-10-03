package com.matatoa.wheremystuff.presentation.placedetailsscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import com.matatoa.wheremystuff.NEW_SUB_PLACE_LENGTH
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
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

    // The field owns its text and cursor. With the value/onValueChange overload the
    // cursor is reset to the start on iOS after every keystroke, so letters typed after
    // the first one land in front of it ("Home" shows up as "omeH").
    val nameState = rememberTextFieldState(initialText = subPlaceName)
    val currentOnSubPlaceNameChange by rememberUpdatedState(newValue = onSubPlaceNameChange)
    LaunchedEffect(nameState) {
        snapshotFlow { nameState.text.toString() }
            .drop(count = 1)
            .collect { currentOnSubPlaceNameChange(it) }
    }

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
                state = nameState,
                inputTransformation = InputTransformation.maxLength(
                    maxLength = NEW_SUB_PLACE_LENGTH,
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                isError = isNameTaken,
                label = {
                    Text(text = stringResource(Res.string.place_details_sub_place_name_label))
                },
                supportingText =
                if (isNameTaken) {
                    {
                        Text(
                            text = stringResource(Res.string.place_details_sub_place_name_taken),
                        )
                    }
                } else if (nameState.text.isNotEmpty()) {
                    {
                        Text(
                            text = "${nameState.text.length}/$NEW_SUB_PLACE_LENGTH",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth(),
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
                enabled = nameState.text.isNotBlank() && !isNameTaken,
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
