package com.example.appnews.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appnews.R
import com.example.appnews.databinding.FragmentRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment(R.layout.fragment_registro) {

    private lateinit var binding : FragmentRegistroBinding
    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistroBinding.bind(view)

        auth = Firebase.auth

        val email = binding.email
        val clave1 = binding.clave
        val clave2 = binding.clave2
        val btnRegistrar = binding.btnRegistrar

        // Al pulsar btn de registrar
        btnRegistrar.setOnClickListener(){
            RegisterFragment().activity?.let { it1 ->

                // Si los campos no están rellenos
                if(!email.toString().isNotEmpty() && !clave1.toString().isNotEmpty() && !clave2.toString().isNotEmpty()){
                    Toast.makeText(context, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show()

                // Si las claves no coinciden
                }else if(clave1.toString().isNotEmpty() != clave2.toString().isNotEmpty()){
                    Toast.makeText(context, "Las claves no coinciden", Toast.LENGTH_SHORT).show()

                // To.do correcto
                }else{
                    auth.createUserWithEmailAndPassword(email.toString(),email.toString())
                        .addOnCompleteListener(it1){ task ->
                            if(task.isSuccessful) {
                                Toast.makeText(context, "Registro correcto", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Algo falló", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }


    }

}