package com.example.appnews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.NewsRepository
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.Source
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Lista2NoticiasFavsViewModel : ViewModel() {

    private val mutableListaNoticias = MutableLiveData<List<ArticleModel>>()
    val listaNoticias: LiveData<List<ArticleModel>> get() = mutableListaNoticias
    val repo : NewsRepository = NewsRepository

    // Cargamos las noticias
    fun init(){
        mutableListaNoticias.value = repo.getNoticiasFavoritas()
        Log.i("NOTICIAS", mutableListaNoticias.value.toString())
    }

    // Agregamos noticia
    fun addNoticia(noticia : ArticleModel){
        repo.addNoticiaFavorita(noticia)
        mutableListaNoticias.value?.plus(noticia)
        Log.i("prueba", "¿Contiene listanoticias la noticia? " + listaNoticias.value?.contains(noticia).toString() )
        init()
    }

    // Borramos noticia
    fun removeNoticia(noticia : ArticleModel){
    //    repo.removeNoticiaFavorita(noticia)
        mutableListaNoticias.value = repo.getNoticiasFavoritas()

    }

}