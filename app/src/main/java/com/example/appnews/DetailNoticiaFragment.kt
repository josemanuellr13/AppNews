package com.example.appnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.appnews.databinding.FragmentDetailNoticiaBinding
import com.example.appnews.model.ArticleModel


class DetailNoticiaFragment : Fragment(R.layout.fragment_detail_noticia) {
    companion object{
        const val noticia = "noticia_clickeada"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailNoticiaBinding.bind(view)
        val noticia = arguments?.getParcelable<ArticleModel>(noticia)


        if (noticia != null) {
            binding.tvAutor.text ="Por " + noticia.source.name
            binding.tvTitulo.text = noticia.title
            binding.tvContenido.text = noticia.description + noticia.content

            if(noticia.urlToImage != null){
                binding.ivFoto.glide(noticia.urlToImage)
            }else{
                binding.ivFoto.glide("https://tecnodemos.junior-report.media/wp-content/uploads/2020/08/Noticia-scaled.jpg")
            }
        }

        // Al clickear btn de fav
        binding.btnFav.setOnClickListener(){

        }

    }
    fun ImageView.glide(url: String){
        Glide.with(this).load(url).into(this)
    }
}
