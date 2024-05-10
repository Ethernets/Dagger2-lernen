package com.example.daggerlernen.common.dependencyinjection.app

import android.app.Application
import com.example.daggerlernen.Constants
import com.example.daggerlernen.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {
    @Singleton
    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun application() = application
    @Singleton
    @Provides
    fun stackoverflowApi(retrofit: Retrofit) = retrofit.create(StackoverflowApi::class.java)
}