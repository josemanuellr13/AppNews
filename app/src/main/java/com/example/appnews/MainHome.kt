package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appnews.databinding.ActivityHomeBinding

class MainHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(NoticiasFragment())



        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(NoticiasFragment())
                R.id.noticia -> replaceFragment(DetailNoticiaFragment())
                R.id.favoritos -> replaceFragment(NoticiasFavoritasFragment())
                else -> { }
            }

            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment,fragment)
        fragmentTransition.commit()
    }
}