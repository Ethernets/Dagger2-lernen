package com.example.daggerlernen.common.dependencyinjection.presentation

import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import com.example.daggerlernen.screens.common.ScreensNavigator
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import com.example.daggerlernen.screens.common.viewsmvc.ViewMvcFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun viewMvcFactory(): ViewMvcFactory
    fun dialogsNavigator(): DialogsNavigator
    fun fetchQuestionsUseCase(): FetchQuestionsUseCase
    fun fetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase
    fun screensNavigator(): ScreensNavigator
}