package com.example.appnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appnews.model.ArticleModel

class ListaNoticiasFavsViewModel : ViewModel() {
    //private val _listaNoticiasFavs : List<ArticleModel> = listOf()


    private val mutableSelectedItem = MutableLiveData<MutableList<ArticleModel>>()
    val selectedItem: LiveData<MutableList<ArticleModel>> get() = mutableSelectedItem

    fun addNoticia(){
        this.selectedItem.add
    }

}