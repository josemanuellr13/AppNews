package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.RemoteConnection
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.NewsApiService
import com.example.appnews.model.NewsDbClient
import kotlinx.coroutines.*

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
            loadNoticias()
        }

        binding.buscar.setOnClickListener(){
            noticiasCustom(binding.texto.text.toString())
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
        Log.e("ENTRADA","Entra en loadNoticias")
        CoroutineScope(Dispatchers.Main).launch{
          binding.progressBar.visibility = View.VISIBLE
            val result = withContext(Dispatchers.IO) { NewsDbClient.service.popularNews(getString(R.string.api_key)) }

            adapterNoticias.noticias = result.articles.subList(0,20)
            binding.progressBar.visibility = View.GONE

        }

    }


    // Buscamos noticias en base al valor pasado
    private fun noticiasCustom(valor: String){
        CoroutineScope(Dispatchers.Main).launch{
            binding.progressBar.visibility = View.VISIBLE
            val result = withContext(Dispatchers.IO) { NewsDbClient.service.customNews(valor,getString(R.string.api_key)) }

            // Como limitar mejor la cant de respuesta que me trae la API
            // pq el teclado se abre solo x defecto¿?
            adapterNoticias.noticias = result.articles.subList(0,10)
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE



        }
    }
}


