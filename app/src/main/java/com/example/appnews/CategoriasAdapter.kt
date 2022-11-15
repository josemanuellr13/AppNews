package com.example.appnews

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.databinding.ApartadoCategoriaItemBinding
import com.example.appnews.databinding.FiltroNoticiasItemBinding
import com.example.appnews.model.CategoriaModel

class CategoriasAdapter( val listener: (CategoriaModel) -> Unit) : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>(){
    var categorias = emptyList<CategoriaModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.apartado_categoria_item,parent,false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filtro_noticias_item ,parent,false)

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
        //private val binding = ApartadoCategoriaItemBinding.bind(view)
        private val binding = FiltroNoticiasItemBinding.bind(view)

        fun bind(categoria: CategoriaModel){
            binding.texto.text = categoria.texto

            if(categoria.seleccionado){
                binding.texto.setBackgroundColor(ContextCompat.getColor(view.context, R.color.blue_500))
                binding.texto.setTextColor(Color.WHITE)
            }else{
                binding.texto.setTextColor(Color.DKGRAY)
                binding.texto.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))

            }
        }
    }

    fun resetearOpciones(){
        for (cat in categorias){
            cat.seleccionado = false
        }
    }
}