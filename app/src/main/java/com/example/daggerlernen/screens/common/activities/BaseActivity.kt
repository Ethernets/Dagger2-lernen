package com.example.daggerlernen.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.MyApplication
import com.example.daggerlernen.common.composition.ActivityCompositionRoot
import com.example.daggerlernen.common.composition.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
    val activityCompositionRoot by lazy { ActivityCompositionRoot(this, appCompositionRoot) }
    protected val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot) }

}