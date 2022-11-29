package com.example.appnews.view

import android.os.Bundle
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
import com.example.appnews.viewmodel.ListaNoticiasFavsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    private val viewModel: ListaNoticiasFavsViewModel by activityViewModels()


    // Metodos
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaNoticias = arguments?.getParcelable<ListaNoticiasFavoritasModel>(LISTA_noticias)

        binding = FragmentNoticiasFavoritasBinding.bind(view)
        binding.rcNoticias.adapter = adapterNoticias

        if(adapterNoticias.itemCount == 0) {
            if (listaNoticias != null) {
                loadNoticiasFavoritas(listaNoticias.lista)
            }
        }

        // Al refrescar el RecyclerView
        binding.srla.setOnRefreshListener {
            if (listaNoticias != null) {
                loadNoticiasFavoritas(listaNoticias.lista)
            }
            binding.srla.isRefreshing = false

        }

//        Toast.makeText(getActivity(), "Cant noticias favs " + listaFavoritas?.size(), Toast.LENGTH_SHORT).show();
    }




    private fun loadNoticiasFavoritas(listaNoticias: List<ArticleModel>){
        var result: Result? = null

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
            binding.progressBar.visibility = View.VISIBLE

            adapterNoticias.noticias = listaNoticias

            // Notificamos cambios
            adapterNoticias.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE

        }



    }
}









