package com.example.appnews.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.appnews.R
import com.example.appnews.databinding.FragmentAgregarTagBinding
import com.example.appnews.databinding.FragmentNoticiasBinding
import com.example.appnews.viewmodel.TagsViewModel
import com.google.android.material.internal.ViewUtils.dpToPx


class AddTagFragment: Fragment(R.layout.fragment_agregar_tag) {
    private lateinit var binding : FragmentAgregarTagBinding
    private val tagViewModel: TagsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAgregarTagBinding.bind(view)
        val img = binding.imageView2
        val rootView: View = requireView().rootView


        // Click al btn de agregar tag
        binding.btnAgregarTag.setOnClickListener {
            if(binding.tvTag.text.toString().isEmpty()){
                Toast.makeText(context, "Ingrese un tag", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                tagViewModel.addTag(binding.tvTag.text.toString())
                Toast.makeText(context, "Tag agregada correctamente", Toast.LENGTH_SHORT).show()
              //  findNavController().navigate(R.id.action_addTagFragment_to_noticiasFragment)
            }

        }
    }
}