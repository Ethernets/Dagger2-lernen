package com.example.daggerlernen.common.composition

import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import com.example.daggerlernen.screens.common.viewsmvc.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager
    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi
    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}