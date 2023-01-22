package com.example.appnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appnews.databinding.ActivityLoginBinding
import com.example.appnews.databinding.RegistroBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : RegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}