package com.example.appnews.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.appnews.databinding.ActivityLoginBinding
import com.example.appnews.viewmodel.Lista2NoticiasFavsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private val viewModel : Lista2NoticiasFavsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        auth = Firebase.auth
        val tvEmail = binding.email
        val tvPassword = binding.clave
        val btnLogin = binding.btnLogin
        val tvCrearCuenta = binding.tvCrearCuenta
        //val btnRedirect = binding.tvRedirectRegister


        tvCrearCuenta.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener(){
            if(tvEmail.text.toString().isNotEmpty() && tvPassword.text.toString().isNotEmpty()){
                auth.signInWithEmailAndPassword(tvEmail.text.toString(), tvPassword.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainHome::class.java)
                            startActivity(intent)
                        }else if(task.exception is FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(this,  "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        }
                    }

            }else{
                Log.i("E","No se pudo iniciar sesion")
            }
        }
    }
}