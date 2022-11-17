package com.example.appnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnews.databinding.FragmentDetailNoticiaBinding




class DetailNoticiaFragment : Fragment(R.layout.fragment_detail_noticia) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailNoticiaBinding.bind(view)

    }
}