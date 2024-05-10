package com.example.daggerlernen.common.dependencyinjection.presentation

import com.example.daggerlernen.screens.questiondetails.QuestionDetailsActivity
import com.example.daggerlernen.screens.questionslist.QuestionsListActivity
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: QuestionsListActivity)
    fun inject(activity: QuestionDetailsActivity)

}