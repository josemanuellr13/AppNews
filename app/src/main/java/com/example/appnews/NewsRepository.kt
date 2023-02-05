package com.example.appnews

import ArticleModel
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

object NewsRepository {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    // Devolvemos noticias favs dado el email de un user
    fun getNoticiasFavoritas(func: (List<ArticleModel>) -> Unit){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        var listaNoticias = mutableListOf<ArticleModel>()

        documentRef.collection("noticiasFavoritas")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val noticia = document.toObject(ArticleModel::class.java)
                    listaNoticias.add(noticia)
                }
                func(listaNoticias.toList())
            }
            .addOnFailureListener { exception ->
                Log.i("TAG", "Error getting documents: ", exception)
            }
    }

    // Agregamos noticia favorita
    fun addNoticiaFavorita(noticia : ArticleModel){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        documentRef.collection("noticiasFavoritas")
            .add(noticia)
            .addOnSuccessListener { subdocumentReference ->
                Log.i("TAG", "Subdocument added with ID: ${subdocumentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.i("TAG", "Error adding subdocument", e)
            }
    }

    // Eliminamos noticia favorita
   fun removeNoticiaFavorita(noticia: ArticleModel){
         val documentRef = db.collection("users").document(currentUser?.email.toString())
         documentRef.collection("noticiasFavoritas")
              .whereEqualTo("title", noticia.title)
              .get()
              .addOnSuccessListener { documents ->
                for (document in documents) {
                     document.reference.delete()
                }
              }
              .addOnFailureListener { exception ->
                Log.i("TAG", "Error getting documents: ", exception)
              }
   }

    // Obtenemos las tags de un usuario
    fun getTags(func: (List<String>) -> Unit){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        var listaTags = mutableListOf<String>()

        documentRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if(documentSnapshot.exists()){
                    val tags = documentSnapshot.get("tags") as List<String>
                    listaTags.add("Añadir tag")
                    for(tag in tags){
                        listaTags.add(tag)
                    }
                }
                func(listaTags.toList())
            }
            .addOnFailureListener { exception ->
                Log.i("TAG", "Error getting documents: ", exception)
            }
    }

    // Agregamos tag
    fun addTag(tag : String){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        documentRef
            .update("tags", FieldValue.arrayUnion(tag))
            .addOnSuccessListener {
                Log.i("TAG", "Tag añadida")
            }
            .addOnFailureListener { e ->
                Log.i("TAG", "Error añadiendo tag", e)
            }
    }

    // Eliminamos tag
    fun removeTag(tag : String){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        documentRef
            .update("tags", FieldValue.arrayRemove(tag))
            .addOnSuccessListener {
                Log.i("TAG", "Tag eliminada")
            }
            .addOnFailureListener { e ->
                Log.i("TAG", "Error eliminando tag", e)
            }
    }

}