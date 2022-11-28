package com.example.appnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.R
import com.example.appnews.databinding.ActivityHomeBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.ListaNoticiasFavoritasModel

class MainHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var noticiasFavs: List<ArticleModel> = listOf()

       // Reemplazamos el primer fragment
        replaceFragment(NoticiasFragment(),null)

        // Indicamos el Nav Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)
       binding.bottomNavigationView.setupWithNavController(navController)


        // Navegacion del menu
        binding.bottomNavigationView.setOnItemSelectedListener {
            val bundle = bundleOf(NoticiasFavoritasFragment.LISTA_noticias to noticiasFavs)
            when(it.itemId){
                R.id.home ->  navController.navigate(R.id.noticiasFragment)
                R.id.favoritos -> navController.navigate(R.id.noticiasFavoritasFragment,bundle)
                else -> { }
            }

            true
        }
    }




    // Reemplazamos fragmento
    // Si el ar
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