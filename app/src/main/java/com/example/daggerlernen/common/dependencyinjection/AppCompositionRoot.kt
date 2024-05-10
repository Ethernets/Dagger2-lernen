package com.example.daggerlernen.common.dependencyinjection

import android.app.Activity
import android.app.Application
import androidx.annotation.UiThread
import com.example.daggerlernen.Constants
import com.example.daggerlernen.networking.StackoverflowApi
import com.example.daggerlernen.screens.common.ScreensNavigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot(val application: Application) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stackoverflowApi: StackoverflowApi by lazy { retrofit.create(StackoverflowApi::class.java) }
    fun screensNavigator(activity: Activity): ScreensNavigator{
        return ScreensNavigator(activity)
    }
}