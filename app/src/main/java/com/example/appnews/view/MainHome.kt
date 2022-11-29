package com.example.appnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.R
import com.example.appnews.databinding.ActivityHomeBinding
import com.example.appnews.model.ArticleModel
import com.example.appnews.model.ListaNoticiasFavoritasModel
import com.example.appnews.model.Source
import com.example.appnews.viewmodel.ListaNoticiasFavsViewModel

class MainHome : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val viewModel: ListaNoticiasFavsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var noticiasFavs: List<ArticleModel> = (listOf(
            ArticleModel(Source("1","CDN"),"Emilie Martin","Announcing the Keyword Research Certification: Create a Personalized Keyword Strategy","Focusing on your audience and using keyword data to your advantage will make for much more successful campaigns than if you were to focus on the typical desirable keywords. With that, we are so excited to announce the launch of our brand-new Keyword Research …","https://moz.com/blog/moz-keyword-research-certification","https://moz.com/images/blog/banners/KWR-Cert-BlogHeader-1180x400.png?w=1200&h=630&q=82&auto=format&fit=crop&dm=1666957435&s=7851da1c97758855b87e54a8de10d135","2022-222-222-2","The heart of your SEO. The foundation for building ideas and thoughts in your industry. The vital link between you and your audience.What are we talking about? Keywords, of course!"),
            ArticleModel(Source("1","CDN"),"Emilie Martin","Announcing the Keyword Research Certification: Create a Personalized Keyword Strategy","Focusing on your audience and using keyword data to your advantage will make for much more successful campaigns than if you were to focus on the typical desirable keywords. With that, we are so excited to announce the launch of our brand-new Keyword Research …","https://moz.com/blog/moz-keyword-research-certification","https://moz.com/images/blog/banners/KWR-Cert-BlogHeader-1180x400.png?w=1200&h=630&q=82&auto=format&fit=crop&dm=1666957435&s=7851da1c97758855b87e54a8de10d135","2022-222-222-2","The heart of your SEO. The foundation for building ideas and thoughts in your industry. The vital link between you and your audience.What are we talking about? Keywords, of course!"),
            ArticleModel(Source("1","CDN"),"Emilie Martin","Announcing the Keyword Research Certification: Create a Personalized Keyword Strategy","Focusing on your audience and using keyword data to your advantage will make for much more successful campaigns than if you were to focus on the typical desirable keywords. With that, we are so excited to announce the launch of our brand-new Keyword Research …","https://moz.com/blog/moz-keyword-research-certification","https://moz.com/images/blog/banners/KWR-Cert-BlogHeader-1180x400.png?w=1200&h=630&q=82&auto=format&fit=crop&dm=1666957435&s=7851da1c97758855b87e54a8de10d135","2022-222-222-2","The heart of your SEO. The foundation for building ideas and thoughts in your industry. The vital link between you and your audience.What are we talking about? Keywords, of course!"),
        ))

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