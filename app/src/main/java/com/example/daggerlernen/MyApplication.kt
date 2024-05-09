package com.example.daggerlernen

import android.app.Application
import com.example.daggerlernen.common.composition.AppCompositionRoot

class MyApplication : Application() {
    lateinit var appCompositionRoot: AppCompositionRoot
    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }

}