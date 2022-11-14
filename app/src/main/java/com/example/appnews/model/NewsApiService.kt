package com.example.appnews.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Definimos las funciones y sus extensiones de las API
interface NewsApiService{


        @GET("top-headlines?language=es")
        suspend fun popularNews(@Query("apikey") apiKey: String): Result

        @GET("everything?searchIn=title")
        suspend fun customNews(@Query("q") content : String ,@Query("apikey") apiKey: String): Result

}