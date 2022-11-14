package com.example.appnews

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appnews.databinding.NoticiaItemBinding
import com.example.appnews.model.ArticleModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoticiasAdapter() : RecyclerView.Adapter<NoticiasAdapter.ViewHolder>(){
    var noticias = emptyList<ArticleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noticia_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]

        holder.bind(noticia)
        holder.itemView.setOnClickListener(){

        }
    }

    override fun getItemCount(): Int = noticias.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val binding = NoticiaItemBinding.bind(view)

        fun bind(article: ArticleModel){
            // Convertir string a fecha hace falta api 26
            binding.tvAutor.text = "Por " +  article.source.name
            binding.tvFecha.text = "16/11/2022 " + "\uD83D\uDD51"
            binding.tvTitulo.text = article.title
            binding.tvDescripcion.text = article.description
            if(article.urlToImage != null){
                binding.ivPortada.glide(article.urlToImage)
            }


        }
    }
}

fun ImageView.glide(url: String){
    Glide.with(this).load(url).into(this)
}