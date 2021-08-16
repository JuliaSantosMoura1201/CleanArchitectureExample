package com.example.domain.di

import com.example.domain.usecases.CheckInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        CheckInUseCase(
            checkInRepository = get()
        )
    }
}