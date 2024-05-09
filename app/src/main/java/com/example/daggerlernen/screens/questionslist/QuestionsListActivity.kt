package com.example.daggerlernen.screens.questionslist

import android.os.Bundle
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import com.example.daggerlernen.questions.Question
import com.example.daggerlernen.screens.common.ScreensNavigator
import com.example.daggerlernen.screens.common.activities.BaseActivity
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsListActivity : BaseActivity(), QuestionsListViewMVC.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMVC: QuestionsListViewMVC
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMVC = compositionRoot.viewMvcFactory.newQuestionsListViewMvc()
        setContentView(viewMVC.rootView)
        fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        dialogsNavigator = compositionRoot.dialogsNavigator
        screensNavigator = compositionRoot.screensNavigator
    }

    override fun onStart() {
        super.onStart()
        viewMVC.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMVC.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMVC.showProgressIndication()
            try {
                when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                    is FetchQuestionsUseCase.Result.Success -> {
                        viewMVC.bindQuestions(result.questions)
                        isDataLoaded = true
                    }

                    is FetchQuestionsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMVC.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(question: Question) {
        screensNavigator.toQuestionDetails(questionId = question.id)

    }
}