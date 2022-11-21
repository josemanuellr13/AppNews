package com.example.appnews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appnews.databinding.FragmentNoticiasBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.NewsDbClient
import com.example.appnews.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoticiasFragment : Fragment(R.layout.fragment_noticias) {
    // Atributos
    private val adapterCategorias = CategoriasAdapter(){
        loadNoticias(it.texto)
    }

    private val adapterNoticias = NoticiasAdapter(){
        val bundle = Bundle()
        bundle.putParcelable("noticia_clickeada",it)
        Log.i("NOTICIA clickeada",it.toString())
        findNavController().navigate(R.id.action_noticiasFragment_to_detailNoticiaFragment, bundle)
    //    findNavController().navigate(R.id.action_noticiasFragment_to_detailNoticiaFragment)

    }

    private lateinit var binding : FragmentNoticiasBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    // Metodos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoticiasBinding.bind(view)

        binding.rcCategorias.adapter = adapterCategorias
        binding.rcNoticias.adapter = adapterNoticias

        loadCategorias()

        if(adapterNoticias.itemCount == 0) {
            loadNoticias(null)
        }

        // Al clickear el btn de buscar
        binding.buscar.setOnClickListener(){
            loadNoticias(binding.texto.text.toString())

        }

        // Al refrescar el RecyclerView
        binding.srla.setOnRefreshListener {
            loadNoticias(null)
            binding.srla.isRefreshing = false
        }

    }

    // Cargamos las categorias
    private fun loadCategorias(){
        val categorias : List<CategoriaModel> = listOf(
            CategoriaModel("Trending",true),
            CategoriaModel("Worldwide",false),
            CategoriaModel("España",false),
            CategoriaModel("Política",false),
            CategoriaModel("Informática",false),
            CategoriaModel("Fútbol",false)

        )

        adapterCategorias.categorias = categorias
    }



    // Si el valor es nulo tomará las noticias + populares
    // Si contiene valor, buscará las noticias relacionadas con el valor
    private fun loadNoticias(valor: String?){
        var result: Result? = null


        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
          binding.progressBar.visibility = View.VISIBLE

            // Obtenemos datos
            if(valor == null)
                 result = withContext(Dispatchers.IO) { NewsDbClient.service.popularNews(getString(R.string.api_key)) }
            else
                 result = withContext(Dispatchers.IO) { NewsDbClient.service.customNews(valor,getString(R.string.api_key)) }


            // Controlamos la cantidad de información mostrada
            if(result!!.articles.size > 20) {
                adapterNoticias.noticias = result!!.articles.subList(0, 20)

            }else{
                    adapterNoticias.noticias = result!!.articles
                }

            // Notificamos cambios
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE

        }



        }
    }









