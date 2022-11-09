package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.RemoteConnection
import android.util.Log
import android.widget.Toast
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.NewsApiService
import com.example.appnews.model.NewsDbClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val adapterCategorias = CategoriasAdapter()
    private val adapterNoticias = NoticiasAdapter()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcCategorias.adapter = adapterCategorias
        binding.rcNoticias.adapter = adapterNoticias

        if(adapterCategorias.itemCount == 0) {
            loadCategorias()
        }

        if(adapterNoticias.itemCount == 0) {
            //loadNoticias()
        }
    }

    private fun loadCategorias(){
        val categorias : List<CategoriaModel> = listOf(
            CategoriaModel("Worldwide"),
            CategoriaModel("España"),
            CategoriaModel("Política"),
            CategoriaModel("Informática")
        )


        adapterCategorias.categorias = categorias
    }


    private fun loadNoticias(){
        Log.e("EEEEEEEEEEE","holaaaa")
        GlobalScope.launch{
            val result = NewsDbClient.service.popularNews(getString(R.string.api_key))
            adapterNoticias.noticias = result.articles
        }

    }
}


