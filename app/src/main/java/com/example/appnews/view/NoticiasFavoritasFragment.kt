package com.example.appnews.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appnews.model.NoticiasAdapter
import com.example.appnews.R
import com.example.appnews.databinding.FragmentNoticiasFavoritasBinding
import com.example.appnews.model.*
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel
import com.example.appnews.viewmodel.ListaNoticiasFavsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class NoticiasFavoritasFragment : Fragment(R.layout.fragment_noticias_favoritas) {
    companion object{
        const val LISTA_noticias = "noticiasFavs"
    }


    // Atributos
    private val adapterNoticias = NoticiasAdapter(){
        val bundle = bundleOf("noticia_clickeada" to it)
        findNavController().navigate(R.id.detailNoticiaFragment, bundle)
    }

    private lateinit var binding : FragmentNoticiasFavoritasBinding

    private val viewModel: Lista2NoticiasFavsViewModel by activityViewModels()


    // Metodos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val listaNoticias = arguments?.getParcelable<ListaNoticiasFavoritasModel>(LISTA_noticias)

        binding = FragmentNoticiasFavoritasBinding.bind(view)
        binding.rcNoticias.adapter = adapterNoticias

        if(adapterNoticias.itemCount == 0) {
            loadNoticiasFavoritas()
        }

        // Al refrescar el RecyclerView
        binding.srla.setOnRefreshListener {
            loadNoticiasFavoritas()
            binding.srla.isRefreshing = false
        }

    }


    private fun loadNoticiasFavoritas() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.VISIBLE
            var lista = viewModel.listaNoticias.value

            if (lista != null) {
                adapterNoticias.noticias = lista
            }

            // Notificamos cambios
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE

        }
    }


}









