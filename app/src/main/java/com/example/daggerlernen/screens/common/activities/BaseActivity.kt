package com.example.daggerlernen.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.MyApplication
import com.example.daggerlernen.common.dependencyinjection.activity.ActivityModule
import com.example.daggerlernen.common.dependencyinjection.activity.DaggerActivityComponent

open class BaseActivity: AppCompatActivity() {
    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(ActivityModule(this))
            .build()
    }


    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent

}