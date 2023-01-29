package com.example.appnews.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    private lateinit var activityContext: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as Activity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        auth = Firebase.auth
        val tvEmail = binding.email
        val tvPassword = binding.clave
        val btnLogin = binding.btnLogin
        val tvCrearCuenta = binding.tvCrearCuenta

        val fragmentManager = fragmentManager

        // Abrimos fragmento de registro
        tvCrearCuenta.setOnClickListener(){
            val newFragment = RegisterFragment()
            val transaction = fragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fg, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }

        // Realizamos login
        btnLogin.setOnClickListener() {
            if (tvEmail.text.toString().isNotEmpty() && tvPassword.text.toString().isNotEmpty()) {
                auth.signInWithEmailAndPassword(tvEmail.text.toString(), tvPassword.text.toString())
                    .addOnCompleteListener(activityContext) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Usuario correcto", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, MainHome::class.java)
                            startActivity(intent)
                        }else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        }
                    }
            }else if(!tvEmail.text.toString().isNotEmpty() || !tvPassword.text.toString().isNotEmpty()){
                Toast.makeText(context,  "Debes rellenar todos los datos", Toast.LENGTH_LONG).show()
            }
        }
    }
}


