package com.example.daggerlernen.networking

import com.example.daggerlernen.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)