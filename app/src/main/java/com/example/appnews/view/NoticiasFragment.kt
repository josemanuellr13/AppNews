package com.example.appnews.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appnews.model.CategoriasAdapter
import com.example.appnews.model.NoticiasAdapter
import com.example.appnews.R
import com.example.appnews.databinding.FragmentNoticiasBinding
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.NewsDbClient
import com.example.appnews.model.Result
import com.example.appnews.viewmodel.TagsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoticiasFragment : Fragment(R.layout.fragment_noticias) {
    private lateinit var binding : FragmentNoticiasBinding
    private val tagViewModel: TagsViewModel by activityViewModels()

    // Le pasamos una funcion al hacer click en una categoria
    private val adapterCategorias = CategoriasAdapter(){ it ->
        if(it.texto === "Añadir tag"){
            findNavController().navigate(R.id.action_noticiasFragment_to_addTagFragment)
        }else{
            loadNoticias(it.texto)
        }
    }

    // Le pasamos una funcion al hacer click en una noticia
    private val adapterNoticias = NoticiasAdapter(){
        val bundle = Bundle()
        bundle.putParcelable("noticia_clickeada",it)
        findNavController().navigate(R.id.action_noticiasFragment_to_detailNoticiaFragment, bundle)
    }


    // Metodos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoticiasBinding.bind(view)
        binding.rcCategorias.adapter = adapterCategorias
        binding.rcNoticias.adapter = adapterNoticias

       tagViewModel.init()

        if(adapterNoticias.itemCount == 0) {
            loadNoticias(null)
        }

        if(adapterCategorias.itemCount == 0) {
            tagViewModel.init()
        }

        tagViewModel.listaTags.observe(viewLifecycleOwner) {
            adapterCategorias.categorias = it.map { tag -> CategoriaModel(tag,false) }
            adapterNoticias.notifyDataSetChanged()
        }

        // Al clickear el btn de buscar
        binding.buscar.setOnClickListener(){
            if(binding.texto.text.toString().isEmpty()){
                loadNoticias(binding.texto.text.toString())
            }else{
                loadNoticias("noticias")
            }

        }

        // Al refrescar el RecyclerView
        binding.srla.setOnRefreshListener {
            loadNoticias(null)
            binding.srla.isRefreshing = false
        }

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
                 result = withContext(Dispatchers.IO) { NewsDbClient.service.customNews(valor,getString(
                     R.string.api_key
                 )) }

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









