package com.example.daggerlernen.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.common.dependencyinjection.app.AppComponent
import com.example.daggerlernen.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity,
    private val appComponent: AppComponent,
) {
    @Provides
    fun activity() = activity
    @Provides
    fun application() = appComponent.application()
    @Provides
    @ActivityScope
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)
    @Provides
    fun layoutInflater() = LayoutInflater.from(activity)
    @Provides
    fun fragmentManager() = activity.supportFragmentManager
    @Provides
    fun stackoverflowApi() = appComponent.stackoverflowApi()
}