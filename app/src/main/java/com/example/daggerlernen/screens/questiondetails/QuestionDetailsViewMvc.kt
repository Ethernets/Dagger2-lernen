package com.example.daggerlernen.screens.questiondetails

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerlernen.R
import com.example.daggerlernen.questions.QuestionWithBody
import com.example.daggerlernen.screens.common.imageloader.ImageLoader
import com.example.daggerlernen.screens.common.toolbar.MyToolbar
import com.example.daggerlernen.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?,
) : BaseViewMvc<QuestionDetailsViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_question_details
) {
    private val toolbar: MyToolbar
    private val swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView
    private val userName: TextView
    private val imgUser: ImageView

    interface Listener {
        fun onBackClicked()
    }

    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)
        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackClicked()
            }
        }
        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false

        userName = findViewById(R.id.txt_user_name)
        imgUser = findViewById(R.id.img_user)

    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    fun bindQuestionBody(question: QuestionWithBody) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(question.body)
        }
        imageLoader.loadImg(question.owner.imageUrl, imgUser)
        userName.text = question.owner.name
    }
}