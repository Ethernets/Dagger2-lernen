package com.example.daggerlernen.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.screens.common.ScreensNavigator
import com.example.daggerlernen.screens.common.activities.BaseActivity
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var questionId: String
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = compositionRoot.viewMvcFactory.newQuestionsDetailsViewMvc()
        setContentView(viewMvc.rootView)
        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        dialogsNavigator = compositionRoot.dialogsNavigator
        screensNavigator = compositionRoot.screensNavigator

        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when(val result = fetchQuestionDetailsUseCase.fetchQuestion(questionId)){
                is FetchQuestionDetailsUseCase.Result.Success -> {
                    viewMvc.bindQuestionBody(result.questions.body)
                }
                    is FetchQuestionDetailsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        onBackPressedDispatcher.onBackPressed()
    }
}