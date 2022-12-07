package com.example.appnews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lista2NoticiasFavsViewModel : ViewModel() {

    private val mutableListaNoticias = MutableLiveData<List<ArticleModel>>()

    val listaNoticias: LiveData<List<ArticleModel>> get() = mutableListaNoticias

    fun init(){
        mutableListaNoticias.value = listOf()
    }

    fun addNoticia(noticia : ArticleModel){
        mutableListaNoticias.value =mutableListaNoticias.value?.plus(noticia)
    }

    fun removeNoticia(noticia : ArticleModel){
        val lista =  mutableListaNoticias.value?.toMutableList()
        if (lista != null) {
            lista.remove(noticia)
        }
        mutableListaNoticias.value = lista

    }

}