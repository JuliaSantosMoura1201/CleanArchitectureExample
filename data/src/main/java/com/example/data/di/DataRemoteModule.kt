package com.example.data.di

import com.example.data.Client.createWebService
import com.example.data.Client.provideHttpClient
import com.example.data.repositoryimpl.CheckInRepositoryImpl
import com.example.data.services.CheckInService
import com.example.domain.repositories.CheckInRepository
import org.koin.dsl.module

val dataModule = module {
    factory { provideHttpClient() }

    single {
        createWebService<CheckInService>(
            okHttpClient = get()
        )
    }
    factory<CheckInRepository> {
        CheckInRepositoryImpl(
            service = get()
        )
    }
}