package com.example.daggerlernen.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.daggerlernen.Constants
import com.example.daggerlernen.R
import com.example.daggerlernen.networking.StackoverflowApi
import com.example.daggerlernen.questions.FetchQuestionsUseCase
import com.example.daggerlernen.questions.Question
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import com.example.daggerlernen.screens.common.dialogs.ServerErrorDialogFragment
import com.example.daggerlernen.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.cancellation.CancellationException

class QuestionsListActivity : AppCompatActivity(), QuestionsListViewMVC.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMVC: QuestionsListViewMVC

    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

    private lateinit var dialogsNavigator: DialogsNavigator

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMVC = QuestionsListViewMVC(LayoutInflater.from(this), null)
        setContentView(viewMVC.rootView)
        fetchQuestionsUseCase = FetchQuestionsUseCase()
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
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
        QuestionDetailsActivity.start(this, question.id)
    }


}