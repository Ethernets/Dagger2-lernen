package com.example.daggerlernen.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.daggerlernen.questions.FetchQuestionDetailsUseCase
import com.example.daggerlernen.screens.common.activities.BaseActivity
import com.example.daggerlernen.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {
    private lateinit var viewMvc: QuestionDetailsViewMvc
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var questionId: String
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = QuestionDetailsViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)
        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        dialogsNavigator = DialogsNavigator(supportFragmentManager)

        // retrieve question ID passed from outside
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