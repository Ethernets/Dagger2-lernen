package com.example.daggerlernen.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.MyApplication

open class BaseActivity: AppCompatActivity() {
    val compositionRoot get() = (application as MyApplication).appCompositionRoot
}