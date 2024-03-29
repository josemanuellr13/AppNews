package com.example.appnews.viewmodel

import ArticleModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.NewsRepository
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
        val funcDatosCargados = {
            listaNoticiasx: List<ArticleModel> -> mutableListaNoticias.value = listaNoticiasx
        }
        repo.getNoticiasFavoritas(funcDatosCargados)
    }

    // Agregamos noticia
    fun addNoticia(noticia : ArticleModel){
        repo.addNoticiaFavorita(noticia)
        init()
    }

    // Borramos noticia
    fun removeNoticia(noticia : ArticleModel){
        repo.removeNoticiaFavorita(noticia)
        init()
    }

}