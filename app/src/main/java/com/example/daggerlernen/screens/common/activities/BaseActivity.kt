package com.example.daggerlernen.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.MyApplication
import com.example.daggerlernen.common.dependencyinjection.ActivityCompositionRoot
import com.example.daggerlernen.common.dependencyinjection.Injector
import com.example.daggerlernen.common.dependencyinjection.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
    private val activityCompositionRoot by lazy { ActivityCompositionRoot(this, appCompositionRoot) }
    protected val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot) }
    protected val injector get() = Injector(compositionRoot)

}