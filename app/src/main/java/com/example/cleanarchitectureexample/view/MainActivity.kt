package com.example.cleanarchitectureexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupButton()
        inscribeObservers()
    }

    private fun setupButton(){
        binding.button.setOnClickListener {
        }
    }

    private fun inscribeObservers(){
    }
}