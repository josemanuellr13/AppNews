package com.example.appnews.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.appnews.R
import com.example.appnews.databinding.ActivityInicioBinding
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel
import com.google.firebase.auth.FirebaseAuth

class InicioActivity  : AppCompatActivity() {

    private lateinit var binding : ActivityInicioBinding
    private lateinit var auth : FirebaseAuth
    private val viewModel : Lista2NoticiasFavsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val navHostFragment = supportFragmentManager.findFragmentById(R.id.fg) as NavHostFragment
        val navController = navHostFragment.navController */
    }
}