package com.example.presentation.di

import com.example.presentation.util.StringLoader
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        StringLoader(androidContext())
    }

}