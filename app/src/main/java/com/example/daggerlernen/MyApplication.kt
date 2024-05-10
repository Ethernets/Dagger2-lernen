package com.example.daggerlernen

import android.app.Application
import com.example.daggerlernen.common.dependencyinjection.app.AppModule
import com.example.daggerlernen.common.dependencyinjection.app.AppComponent
import com.example.daggerlernen.common.dependencyinjection.app.DaggerAppComponent

class MyApplication : Application() {
    public val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    override fun onCreate() {
        super.onCreate()
    }

}