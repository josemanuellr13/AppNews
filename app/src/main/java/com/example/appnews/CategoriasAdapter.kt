package com.example.appnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.databinding.ApartadoCategoriaItemBinding
import com.example.appnews.model.CategoriaModel
import java.time.chrono.JapaneseEra.values

class CategoriasAdapter() : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>(){
    var categorias = emptyList<CategoriaModel>()
    var selectedCategoria = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apartado_categoria_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]

        holder.bind(categoria,true)
        holder.itemView.setOnClickListener(){
            this.selectedCategoria = position
        }
    }

    override fun getItemCount(): Int = categorias.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val binding = ApartadoCategoriaItemBinding.bind(view)

        fun bind(categoria: CategoriaModel, isSelected: Boolean){
            binding.texto.text = categoria.texto

            if(isSelected){
                binding.texto.setPressed(true)
            }
        }
    }
}