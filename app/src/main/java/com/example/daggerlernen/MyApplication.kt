package com.example.daggerlernen

import android.app.Application
import com.example.daggerlernen.common.dependencyinjection.AppCompositionRoot

class MyApplication : Application() {
    lateinit var appCompositionRoot: AppCompositionRoot
    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot(this)
        super.onCreate()
    }

}