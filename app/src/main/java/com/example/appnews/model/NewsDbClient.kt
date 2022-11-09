package com.example.appnews.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsDbClient {
    private val builder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: NewsApiService = builder.create(NewsApiService::class.java)
}