package com.example.appnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            binding.tvAutor.text = noticia.title
        }

    }
}