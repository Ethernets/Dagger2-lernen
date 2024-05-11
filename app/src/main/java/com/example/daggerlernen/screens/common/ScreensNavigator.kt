package com.example.daggerlernen.screens.common

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlernen.common.dependencyinjection.activity.ActivityScope
import com.example.daggerlernen.screens.questiondetails.QuestionDetailsActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigator @Inject constructor(private val activityScope: AppCompatActivity) {
    fun toQuestionDetails(questionId: String){
        QuestionDetailsActivity.start(activityScope, questionId)
    }
}