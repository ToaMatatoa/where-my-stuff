package com.matatoa.wheremystuff.di

import com.matatoa.wheremystuff.presentation.StartScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(constructor = ::StartScreenViewModel)
}
