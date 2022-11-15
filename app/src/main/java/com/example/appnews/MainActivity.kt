package com.example.appnews

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.NewsDbClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    // Atributos
    private val adapterCategorias = CategoriasAdapter(){
        noticiasCustom("cheese")
    }
    private val adapterNoticias = NoticiasAdapter()
    private lateinit var binding : ActivityMainBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    // Metodos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcCategorias.adapter = adapterCategorias
        binding.rcNoticias.adapter = adapterNoticias

        loadCategorias()

        if(adapterNoticias.itemCount == 0) {
            loadNoticias()
        }

        // Al clickear el btn de buscar
        binding.buscar.setOnClickListener(){
            noticiasCustom(binding.texto.text.toString())
        }

        // Al refrescar el RecyclerView
        binding.srla.setOnRefreshListener {
            loadNoticias()
            binding.srla.isRefreshing = false

        }
    }

    // Cargamos las categorias
    private fun loadCategorias(){
        val categorias : List<CategoriaModel> = listOf(
            CategoriaModel("Worldwide",false),
            CategoriaModel("España",false),
            CategoriaModel("Política",false),
            CategoriaModel("Informática",false)
        )

        adapterCategorias.categorias = categorias
    }


    private fun loadNoticias(){
        Log.e("ENTRADA","Entra en loadNoticias")
        CoroutineScope(Dispatchers.Main).launch{
          binding.progressBar.visibility = View.VISIBLE
            val result = withContext(Dispatchers.IO) { NewsDbClient.service.popularNews(getString(R.string.api_key)) }

            adapterNoticias.noticias = result.articles.subList(0,20)
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE

        }

    }


    // Buscamos noticias en base al valor pasado
    private fun noticiasCustom(valor: String){
        CoroutineScope(Dispatchers.Main).launch{
            binding.progressBar.visibility = View.VISIBLE
            Log.i("VALOR",valor)
            val result = withContext(Dispatchers.IO) { NewsDbClient.service.customNews(valor,getString(R.string.api_key)) }

            adapterNoticias.noticias = result.articles.subList(0,10)
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE



        }
    }


}


