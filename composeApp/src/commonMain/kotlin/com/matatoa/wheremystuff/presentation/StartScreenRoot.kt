package com.matatoa.wheremystuff.presentation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<StartScreenViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    StartScreen(
        state = state,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}
