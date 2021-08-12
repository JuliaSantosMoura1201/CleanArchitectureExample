package com.example.cleanarchitectureexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.ActivityMainBinding
import com.example.presentation.viewModels.CheckInViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<CheckInViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupButton()
        inscribeObservers()
    }

    private fun setupButton(){
        binding.button.setOnClickListener {
            viewModel.makeCheckIn(
                email = binding.editTextTextPersonEmail.text.toString(),
                eventId = binding.editTextTextPersonId.text.toString(),
                name = binding.editTextTextPersonName.text.toString()
            )
        }
    }

    private fun inscribeObservers(){
        viewModel.error.observe(this, {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
        viewModel.success.observe(this, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })
    }
}