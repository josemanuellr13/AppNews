package com.example.appnews.model

import ArticleModel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ListaNoticiasFavoritasModel (

    var lista : List<ArticleModel>

): Parcelable {

}