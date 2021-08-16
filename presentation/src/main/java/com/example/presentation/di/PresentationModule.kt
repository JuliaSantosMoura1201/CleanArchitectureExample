package com.example.presentation.di

import com.example.presentation.viewModels.CheckInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel{
        CheckInViewModel(
            checkInUseCase = get()
        )
    }
}