package com.example.appnews.model

import retrofit2.http.GET
import retrofit2.http.Query

// Definimos las funciones y sus extensiones de las API
interface NewsApiService{


        @GET("top-headlines?country=us&apiKey=<apikey>")
        suspend fun popularNews(@Query("apiKey") apiKey: String): Result

}