package com.example.appnews.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ListaNoticiasFavoritasModel (

    var lista : List<ArticleModel>

): Parcelable {

}