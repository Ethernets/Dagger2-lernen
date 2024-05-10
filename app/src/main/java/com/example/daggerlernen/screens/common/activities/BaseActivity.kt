package com.example.daggerlernen.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.MyApplication
import com.example.daggerlernen.common.dependencyinjection.activity.ActivityModule
import com.example.daggerlernen.common.dependencyinjection.DaggerActivityComponent
import com.example.daggerlernen.common.dependencyinjection.DaggerPresentationComponent
import com.example.daggerlernen.common.dependencyinjection.Injector
import com.example.daggerlernen.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appCompositionRoot))
            .build()
    }


    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)

}