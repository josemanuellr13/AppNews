package com.example.appnews.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.appnews.R
import com.example.appnews.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        auth = Firebase.auth
        val tvEmail = binding.email
        val tvPassword = binding.clave
        val btnLogin = binding.btnLogin
        val tvCrearCuenta = binding.tvCrearCuenta

        val navController = Navigation.findNavController(view)
        val navControllerx = NavHostFragment.findNavController(R.id.fg)

        tvCrearCuenta.setOnClickListener(){
           // navController.navigate()
        }


        btnLogin.setOnClickListener(){
            if(tvEmail.text.toString().isNotEmpty() && tvPassword.text.toString().isNotEmpty()){
                LoginFragment().activity?.let { it1 ->
                    auth.signInWithEmailAndPassword(tvEmail.text.toString(), tvPassword.text.toString())
                        .addOnCompleteListener(it1){ task ->
                            if(task.isSuccessful){

                                Toast.makeText(context, "Usuario correcto", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, MainHome::class.java)
                                startActivity(intent)

                            }else if(task.exception is FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(context,  "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                            }
                        }
                }

            }else if(!tvEmail.text.toString().isNotEmpty() || !tvPassword.text.toString().isNotEmpty()){
                Toast.makeText(context,  "Debes rellenar todos los datos", Toast.LENGTH_LONG).show()
            }
        }
    }
}


