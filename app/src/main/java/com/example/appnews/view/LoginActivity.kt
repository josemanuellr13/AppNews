package com.example.appnews.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.appnews.R
import com.example.appnews.databinding.ActivityHomeBinding
import com.example.appnews.databinding.ActivityLoginBinding
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private val viewModel : Lista2NoticiasFavsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener(){
            val intent = Intent(this, MainHome::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth
        val tvEmail = binding.tvEmail
        val tvPassword = binding.tvPassword
        val btnLogin = binding.btnLogin
        val btnRedirect = binding.tvRedirectRegister



        btnLogin.setOnClickListener(){
            if(tvEmail.text.toString().isNotEmpty() && tvPassword.text.toString().isNotEmpty()){
                auth.signInWithEmailAndPassword(tvEmail.text.toString(),tvPassword.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,  task.exception.toString(), Toast.LENGTH_LONG).show()
                            Log.e("ERROR",task.exception.toString())
                            Log.e("ERROR",tvEmail.toString())

                        }
                    }

            }else{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}