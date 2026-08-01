package com.matatoa.wheremystuff

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import com.matatoa.wheremystuff.navigation.AppNavigation

@Composable
@Preview
fun WhereMyStuff() {
    WhereMyStuffTheme {
        AppNavigation()
    }
}
