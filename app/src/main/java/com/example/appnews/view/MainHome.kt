package com.example.appnews.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.R
import com.example.appnews.databinding.ActivityHomeBinding
import com.example.appnews.model.ListaNoticiasFavoritasModel
import com.example.appnews.model.Source
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel
import com.example.appnews.viewmodel.ListaNoticiasFavsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val viewModel : Lista2NoticiasFavsViewModel by viewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Conexion
        val currentUser = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        viewModel.init()

       // Reemplazamos el primer fragment
        replaceFragment(NoticiasFragment(),null)

        // Indicamos el Nav Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)
       binding.bottomNavigationView.setupWithNavController(navController)


        // Navegacion del menu
        binding.bottomNavigationView.setOnItemSelectedListener {
          //  val bundle = bundleOf(NoticiasFavoritasFragment.LISTA_noticias to noticiasFavs)
            when(it.itemId){
                R.id.home ->  navController.navigate(R.id.noticiasFragment)
                R.id.favoritos -> navController.navigate(R.id.noticiasFavoritasFragment)
                else -> { }
            }
            true

        }
    }




    // Reemplazamos fragmento
    private fun replaceFragment(fragment : Fragment, noticias : ListaNoticiasFavoritasModel?) {
        var bundle = Bundle()
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()

        if(noticias != null){
            bundle = bundleOf(NoticiasFavoritasFragment.LISTA_noticias to noticias)
            fragment.arguments = bundle
        }

        fragmentTransition.replace(R.id.fragment2,fragment)
        fragmentTransition.commit()
    }
}