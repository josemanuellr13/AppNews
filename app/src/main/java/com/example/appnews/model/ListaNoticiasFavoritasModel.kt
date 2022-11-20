package com.example.appnews.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ListaNoticiasFavoritasModel (

    val lista : List<ArticleModel>

): Parcelable {

}