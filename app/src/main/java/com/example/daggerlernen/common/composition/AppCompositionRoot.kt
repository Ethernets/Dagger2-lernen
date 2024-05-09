package com.example.daggerlernen.common.composition

import android.app.Activity
import androidx.annotation.UiThread
import com.example.daggerlernen.Constants
import com.example.daggerlernen.networking.StackoverflowApi
import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import com.example.daggerlernen.screens.common.ScreensNavigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot {
    private var _retrofit: Retrofit? = null
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