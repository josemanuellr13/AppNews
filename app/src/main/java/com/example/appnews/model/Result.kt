package com.example.appnews.model


data class Result(

    val status : String,
    val totalResults : Int,
    val articles : List<ArticleModel>
)