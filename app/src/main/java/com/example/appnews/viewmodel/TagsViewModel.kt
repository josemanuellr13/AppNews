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

class TagsViewModel : ViewModel() {

    private val mutableListaTags = MutableLiveData<List<String>>()
    val listaTags: LiveData<List<String>> get() = mutableListaTags
    val repo : NewsRepository = NewsRepository

    // Cargamos las noticias
    fun init(){
        val funcDatosCargados = {
                listaTags: List<String> -> mutableListaTags.value = listaTags
        }
        repo.getTags(funcDatosCargados)
    }

    // Agregamos noticia
    fun addTag(tag : String){
        repo.addTag(tag)
        init()
    }

    // Borramos noticia
    fun removeTag(tag : String){
        repo.removeTag(tag)
        init()
    }

}