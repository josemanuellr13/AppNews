package com.example.appnews

import android.util.Log
import com.example.appnews.model.ArticleModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

object NewsRepository {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    // Devolvemos noticias favs dado el email de un user
    fun getNoticiasFavoritas() : List<ArticleModel>{
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        var resultado = listOf<ArticleModel>()
        documentRef.collection("noticiasFavoritas").get().addOnSuccessListener { subcollection ->
            resultado = subcollection.toObjects(ArticleModel::class.java)
        }
        return resultado
    }

    // Agregamos noticia favorita
    fun addNoticiaFavorita(noticia : ArticleModel){
        val documentRef = db.collection("users").document(currentUser?.email.toString())
        documentRef.collection("noticiasFavoritas")
            .add(noticia)
            .addOnSuccessListener { subdocumentReference ->
                Log.i("TAG", "Subdocument added with ID: ${subdocumentReference.id}")
            }
            .addOnFailureListener { e -> Log.i("TAG", "Error adding subdocument", e) }
    }

    // Eliminamos noticia favorita
   /* fun removeNoticiaFavorita(noticia : ArticleModel){
        val documentReference = db.collection("users").document(currentUser?.email.toString())
        documentReference.collection("noticiasFAavoritas").update("noticias", FieldValue.arrayRemove(noticia))
            .addOnSuccessListener { Log.i("resultado", "Valor eliminado exitosamente") }
            .addOnFailureListener { Log.i("resultado", "Error al eliminar valor", it) }
    } */

}