package com.matatoa.wheremystuff.presentation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StartScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<StartScreenViewModel>()

    StartScreen(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}
