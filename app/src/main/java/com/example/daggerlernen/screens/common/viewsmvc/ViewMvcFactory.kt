package com.example.daggerlernen.screens.common.viewsmvc

import android.view.LayoutInflater
import com.example.daggerlernen.screens.common.imageloader.ImageLoader
import com.example.daggerlernen.screens.questiondetails.QuestionDetailsViewMvc
import com.example.daggerlernen.screens.questionslist.QuestionsListViewMVC
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    ) {
    fun newQuestionsListViewMvc(): QuestionsListViewMVC{
        return QuestionsListViewMVC(layoutInflater, null)
    }

    fun newQuestionsDetailsViewMvc(): QuestionDetailsViewMvc{
        return QuestionDetailsViewMvc(layoutInflater, imageLoader,null,)
    }
}