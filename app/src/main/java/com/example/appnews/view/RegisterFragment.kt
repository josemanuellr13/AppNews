package com.example.appnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appnews.R
import com.example.appnews.databinding.FragmentLoginBinding
import com.example.appnews.databinding.FragmentRegistroBinding


class RegisterFragment : Fragment(R.layout.fragment_registro) {

    private lateinit var binding : FragmentRegistroBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistroBinding.bind(view)
    }

}