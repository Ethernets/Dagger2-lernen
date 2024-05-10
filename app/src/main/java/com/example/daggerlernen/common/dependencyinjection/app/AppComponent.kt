package com.example.daggerlernen.common.dependencyinjection.app

import android.app.Application
import com.example.daggerlernen.networking.StackoverflowApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun application(): Application
    fun stackoverflowApi(): StackoverflowApi
}