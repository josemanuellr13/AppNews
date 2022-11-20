package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.databinding.ActivityHomeBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.CategoriaModel
import com.example.appnews.model.ListaNoticiasFavoritasModel

class MainHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // Reemplazamos el primer fragment
        replaceFragment(NoticiasFragment(),null)

        // Indicamos el Nav Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        //setupActionBarWithNavController(navController)
       binding.bottomNavigationView.setupWithNavController(navController)


        // Navegacion del menu
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(NoticiasFragment(),null)
                R.id.favoritos -> replaceFragment(NoticiasFavoritasFragment(),getNoticiasFavs())
                else -> { }
            }

            true
        }
    }

    private fun getNoticiasFavs(): ListaNoticiasFavoritasModel{
        val noticiasFavs: List<ArticleModel> =  listOf(
            ArticleModel(Source("1","CDN"),"Yo","Esto es un ","esto es una descripcion","esorojotjrorjoer","","2022-222-222-2","Esto es un ejemplo ojala funcioen xd"),
            ArticleModel(Source("1","CDN"),"Yo","Esto es un ","esto es una descripcion","esorojotjrorjoer","","2022-222-222-2","Esto es un ejemplo ojala funcioen xd")
        ,ArticleModel(Source("1","CDN"),"Yo","Esto es un ","esto es una descripcion","esorojotjrorjoer","","2022-222-222-2","Esto es un ejemplo ojala funcioen xd")
        )

        return ListaNoticiasFavoritasModel(noticiasFavs)
    }


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