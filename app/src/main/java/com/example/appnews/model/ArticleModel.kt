package com.example.appnews.model

import android.os.Parcelable
import com.example.appnews.Source
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ArticleModel (

    val source : Source?,
    val author : String,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String?,
    val publishedAt : String,
    val content : String?
): Parcelable {

}