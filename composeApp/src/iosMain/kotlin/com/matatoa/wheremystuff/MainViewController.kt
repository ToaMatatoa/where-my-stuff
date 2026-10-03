package com.matatoa.wheremystuff

import androidx.compose.ui.window.ComposeUIViewController
import com.matatoa.wheremystuff.di.initKoin

@Suppress("ktlint:standard:function-naming", "FunctionNaming")
fun MainViewController() = ComposeUIViewController { WhereMyStuff() }

fun initKoinIos() {
    initKoin()
}
