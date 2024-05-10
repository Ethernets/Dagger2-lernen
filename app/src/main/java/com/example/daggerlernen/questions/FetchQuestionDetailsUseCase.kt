package com.example.daggerlernen.questions

import com.example.daggerlernen.Constants
import com.example.daggerlernen.networking.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.cancellation.CancellationException

class FetchQuestionDetailsUseCase(private val stackoverflowApi: StackoverflowApi) {
    sealed class Result {
        data class Success(val questions: QuestionWithBody) : Result()
        object Failure : Result()
    }
    suspend fun fetchQuestion(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.question)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }

        }
    }
}