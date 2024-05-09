package com.example.daggerlernen.common.composition

import com.example.daggerlernen.Constants
import com.example.daggerlernen.networking.StackoverflowApi
import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)
}