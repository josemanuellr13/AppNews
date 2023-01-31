package com.example.appnews.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel (

    val source : Source,
    val author : String?,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String?,
    val publishedAt : String,
    val content : String?
): Parcelable {
    constructor(): this(
        source = Source(),
        author = null,
        title = "",
        description = "",
        url = "",
        urlToImage = null,
        publishedAt = "",
        content = null
    )
}