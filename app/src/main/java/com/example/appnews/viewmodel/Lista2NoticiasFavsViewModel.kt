package com.example.appnews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val currentUser = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    // Cargamos las noticias
    fun init(){
        val collectionReference = db.collection("users").document(currentUser?.email.toString())

        collectionReference.get().addOnSuccessListener { result ->
            val valorAtributo = result.get("noticias") as List<ArticleModel>
            mutableListaNoticias.value = valorAtributo
        }

    }

    fun addNoticia(noticia : ArticleModel){
        val documentReference = db.collection("users").document(currentUser?.email.toString())
        documentReference.update("noticias", FieldValue.arrayUnion(noticia))
            .addOnSuccessListener { Log.i("resultado", "Valor agregado exitosamente") }
            .addOnFailureListener { Log.i("resultado", "Error al agregar valor", it) }
        mutableListaNoticias.value?.plus(noticia)
        Log.i("prueba", "¿Contiene listanoticias la noticia? " + listaNoticias.value?.contains(noticia).toString() )
        init()
    }

    fun removeNoticia(noticia : ArticleModel){
        val lista =  mutableListaNoticias.value?.toMutableList()
        if (lista != null) {
            lista.remove(noticia)
        }
        mutableListaNoticias.value = lista

    }

}