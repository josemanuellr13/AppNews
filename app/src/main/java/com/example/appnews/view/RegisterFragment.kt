package com.example.appnews.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appnews.NewsRepository.db
import com.example.appnews.R
import com.example.appnews.databinding.FragmentRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment(R.layout.fragment_registro) {

    private lateinit var binding : FragmentRegistroBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var activityContext: Activity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistroBinding.bind(view)

        auth = Firebase.auth

        val email = binding.email.text
        val clave1 = binding.clave.text
        val clave2 = binding.clave2.text
        val btnRegistrar = binding.btnRegistrar

        // Al pulsar btn de registrar
        btnRegistrar.setOnClickListener(){

                // Si checkbox no está marcado
                if(!binding.checkBox.isChecked) { Toast.makeText( context,
                        "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                }
                // Si los campos no están rellenos
                if(email.toString().isEmpty() || clave1.toString().isEmpty() || clave2.toString().isEmpty()){
                    Toast.makeText(context, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show()

                // Si las claves no coinciden
                }else if(clave1.toString().isNotEmpty() != clave2.toString().isNotEmpty()){
                    Toast.makeText(context, "Las claves no coinciden", Toast.LENGTH_SHORT).show()

                // To.do correcto
                }else if(email.toString().isNotEmpty() && clave1.toString().isNotEmpty() && clave2.toString().isNotEmpty() && binding.checkBox.isChecked){
                    auth.createUserWithEmailAndPassword(email.toString(), clave1.toString())
                        .addOnCompleteListener(activityContext){ task ->
                            if(task.isSuccessful) {
                                Toast.makeText(context, "Registro correcto", Toast.LENGTH_SHORT).show()

                                // Crear registro de dicho usuario en Firebase
                                val user = hashMapOf(
                                    "email" to email.toString(),
                                    "tags" to listOf<String>("Deporte"),
                                )

                                // usuario -> email -> noticiasFavoritas -> noticia
                                val parentDocument = db.collection("users").document(email.toString())

                                parentDocument.set(user)
                                    .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                                    .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }

                                val intent = Intent(context, MainHome::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(context, "Algo falló", Toast.LENGTH_SHORT).show()
                            }
                        }
                }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as Activity
    }

}