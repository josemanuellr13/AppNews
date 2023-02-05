package com.example.appnews.model

import ArticleModel


data class Result(

    val status : String,
    val totalResults : Int,
    val articles : MutableList<ArticleModel>
)