package com.example.daggerlernen.screens.common.viewsmvc

import android.view.LayoutInflater
import com.example.daggerlernen.screens.questiondetails.QuestionDetailsViewMvc
import com.example.daggerlernen.screens.questionslist.QuestionsListViewMVC

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newQuestionsListViewMvc(): QuestionsListViewMVC{
        return QuestionsListViewMVC(layoutInflater, null)
    }

    fun newQuestionsDetailsViewMvc(): QuestionDetailsViewMvc{
        return QuestionDetailsViewMvc(layoutInflater, null)
    }
}