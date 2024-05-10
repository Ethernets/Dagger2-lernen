package com.example.daggerlernen

import android.app.Application
import com.example.daggerlernen.common.dependencyinjection.app.AppModule
import com.example.daggerlernen.common.dependencyinjection.DaggerAppComponent

class MyApplication : Application() {
    public val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    override fun onCreate() {
        super.onCreate()
    }

}