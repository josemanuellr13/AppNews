package com.example.appnews

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.databinding.ApartadoCategoriaItemBinding
import com.example.appnews.model.CategoriaModel

class CategoriasAdapter( val listener: () -> Unit) : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>(){
    var categorias = emptyList<CategoriaModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apartado_categoria_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var categoria = categorias[position]

        holder.bind(categoria,position)

        holder.itemView.setOnClickListener(){
            categoria.seleccionado = true

            this.notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int = categorias.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val binding = ApartadoCategoriaItemBinding.bind(view)

        fun bind(categoria: CategoriaModel, position: Int){
            binding.texto.text = categoria.texto

            if(categoria.seleccionado){
                binding.texto.setBackgroundColor(Color.DKGRAY)
                binding.texto.setTextColor(Color.WHITE)
            }
        }
    }
}