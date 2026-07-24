package com.matatoa.wheremystuff.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.designsystem.Strings
import com.matatoa.wheremystuff.designsystem.TopBar
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopBar(
            title = Strings.Common.TOP_BAR_TITLE,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    WhereMyStuffTheme {
        StartScreen()
    }
}
