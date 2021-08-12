package com.example.cleanarchitectureexample

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                domainModule,
                dataModule,
                presentationModule
            )
        }
    }
}