package com.example.appnews.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        //val btnRedirect = binding.tvRedirectRegister


        tvCrearCuenta.setOnClickListener(){
            val newFragment = RegisterFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.action_loginFragment_to_registerFragment, newFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
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

            }else{
                Log.i("E","No se pudo iniciar sesion")
            }
        }
    }
}


