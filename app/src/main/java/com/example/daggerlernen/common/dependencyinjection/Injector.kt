package com.example.daggerlernen.common.dependencyinjection

import com.example.daggerlernen.screens.questiondetails.QuestionDetailsActivity
import com.example.daggerlernen.screens.questionslist.QuestionsListActivity

class Injector(private val compositionRoot: PresentationCompositionRoot) {
    fun inject(questionsListActivity: QuestionsListActivity) {
        questionsListActivity.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        questionsListActivity.dialogsNavigator = compositionRoot.dialogsNavigator
        questionsListActivity.screensNavigator = compositionRoot.screensNavigator
        questionsListActivity.viewMvcFactory = compositionRoot.viewMvcFactory
    }
    fun inject(detailsActivity: QuestionDetailsActivity) {
        detailsActivity.fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        detailsActivity.dialogsNavigator = compositionRoot.dialogsNavigator
        detailsActivity.screensNavigator = compositionRoot.screensNavigator
        detailsActivity.viewMvcFactory = compositionRoot.viewMvcFactory

    }
}