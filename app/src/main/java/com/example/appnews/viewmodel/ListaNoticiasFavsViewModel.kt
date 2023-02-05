package com.example.appnews.viewmodel

import ArticleModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaNoticiasFavsViewModel : ViewModel() {

    //val prueba : MutableLiveData<String>() = ""
    private val mutableListaNoticias = MutableLiveData<MutableList<ArticleModel>>()

    val listaNoticias: LiveData<MutableList<ArticleModel>> get() = mutableListaNoticias


    fun init(){
        var lista : MutableList<ArticleModel> = ArrayList()
        this.mutableListaNoticias.postValue(lista)
    }


    fun addNoticia(noticia : ArticleModel){

        Log.i("CONTENIDO", listaNoticias.value.toString())

        var lista = mutableListaNoticias.value
        lista?.add(noticia)

        // mutableListaNoticias.value?.toMutableList()?.add(noticia)
        //var lista: MutableList<ArticleModel> =
        //lista.add(noticia)
        this.mutableListaNoticias.postValue(lista)

        Log.i("CONTENIDO", mutableListaNoticias.value.toString())
        Log.i("CONTENIDO", listaNoticias.value.toString())
    }

}