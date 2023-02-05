package com.example.appnews.model

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.databinding.FiltroNoticiasItemBinding

class CategoriasAdapter( val listener: (CategoriaModel) -> Unit) : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>(){
    var categorias = emptyList<CategoriaModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filtro_noticias_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var categoria = categorias[position]
        holder.bind(categoria)

        holder.itemView.setOnClickListener {
            resetearOpciones()
            categoria.seleccionado = true
            listener(categoria)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = categorias.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val binding = FiltroNoticiasItemBinding.bind(view)

        fun bind(categoria: CategoriaModel){
            binding.texto.text = categoria.texto
            Log.i("CATEGORIA",categoria.texto)


            if(categoria.seleccionado){
                binding.texto.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.blue_300
                ))
                binding.texto.setTextColor(Color.WHITE)
            }else{
                binding.texto.setTextColor(Color.DKGRAY)
                binding.texto.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
            }

            if(categoria.texto === "Añadir tag"){
                binding.texto.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.blue_500
                ))
                binding.texto.setTextColor(Color.WHITE)
            }
        }
    }

    fun resetearOpciones(){
        for (cat in categorias){
            cat.seleccionado = false
        }
    }
}