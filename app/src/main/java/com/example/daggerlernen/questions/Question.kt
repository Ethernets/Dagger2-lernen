package com.example.daggerlernen.questions

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("title") val title: String,
    @SerializedName("question_id") val id: String
)