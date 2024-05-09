package com.example.daggerlernen.screens.common

import android.content.Context
import com.example.daggerlernen.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val context: Context) {
    fun toQuestionDetails(questionId: String){
        QuestionDetailsActivity.start(context, questionId)
    }
}