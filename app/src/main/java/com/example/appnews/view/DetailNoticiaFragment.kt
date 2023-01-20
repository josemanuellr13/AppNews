package com.example.appnews.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.appnews.R
import com.example.appnews.databinding.FragmentDetailNoticiaBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel


class DetailNoticiaFragment : Fragment(R.layout.fragment_detail_noticia) {
    companion object{
        const val noticia = "noticia_clickeada"
    }

    private val viewModel: Lista2NoticiasFavsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailNoticiaBinding.bind(view)
        val noticia = arguments?.getParcelable<ArticleModel>(noticia)


        if (noticia != null) {
            // Comprobamos si la noticia esta en la lista de favoritas
            viewModel.listaNoticias.observe(viewLifecycleOwner, Observer {
                if(it.contains(noticia)){
                    binding.btnFav.setColorFilter(Color.RED)
                }else{
                    binding.btnFav.setColorFilter(Color.GRAY)
                }
            })


            // Asignamos valores
            binding.tvAutor.text ="Por " + noticia.source.name
            binding.tvTitulo.text = noticia.title
            binding.tvContenido.text = noticia.description

            if(noticia.urlToImage != null){
                binding.ivFoto.glide(noticia.urlToImage)
            }else{
                binding.ivFoto.glide("https://tecnodemos.junior-report.media/wp-content/uploads/2020/08/Noticia-scaled.jpg")
            }

        }

        // Añadimos/quitamos de noticias favoritas
        binding.btnFav.setOnClickListener(){

            if(viewModel.listaNoticias.value?.contains(noticia) == true){
                if (noticia != null)
                    viewModel.removeNoticia(noticia)

            }else{
                if (noticia != null)
                    viewModel.addNoticia(noticia)
            }
        }


        // Al Click, abre la noticia en URL
        binding.btnAbrirUrl.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(noticia?.url ))
            startActivity(browserIntent)
        }

    }


    fun ImageView.glide(url: String){
        Glide.with(this).load(url).into(this)
    }
}
