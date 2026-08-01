package com.matatoa.wheremystuff

import androidx.compose.ui.window.ComposeUIViewController
import com.matatoa.wheremystuff.di.initKoin

fun MainViewController() = ComposeUIViewController { WhereMyStuff() }

fun initKoinIos() {
    initKoin()
}
