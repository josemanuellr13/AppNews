package com.example.appnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appnews.databinding.FragmentNoticiasFavoritasBinding
import com.example.appnews.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoticiasFavoritasFragment : Fragment(R.layout.fragment_noticias_favoritas) {
    companion object{

        const val LISTA_noticias = "noticiasFavs"
    }
    // Atributos
    private val adapterNoticias = NoticiasAdapter(){
        findNavController().navigate(R.id.action_noticiasFragment_to_detailNoticiaFragment)
    }

    private lateinit var binding : FragmentNoticiasFavoritasBinding



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









