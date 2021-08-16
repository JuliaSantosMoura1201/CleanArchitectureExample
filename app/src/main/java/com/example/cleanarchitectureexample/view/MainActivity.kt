package com.example.cleanarchitectureexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitectureexample.R
import com.example.cleanarchitectureexample.databinding.ActivityMainBinding
import com.example.domain.exceptions.CheckInException
import com.example.presentation.viewModels.CheckInViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<CheckInViewModel>()

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
                name = binding.editTextTextPersonName.text.toString(),
            )
        }
    }

    private fun inscribeObservers(){
        viewModel.checkInException.observe(this, {
            val message = when(it){
                CheckInException.EmptyEmailException -> R.string.empty_email_message
                CheckInException.EmptyNameException -> R.string.empty_name_message
                CheckInException.InvalidEmailException -> R.string.invalid_email_message
                CheckInException.InvalidEventId -> R.string.invalid_id_message
            }
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        })

        viewModel.success.observe(this, {
            Snackbar.make(binding.root, R.string.success_message, Snackbar.LENGTH_LONG).show()
        })
        viewModel.noNetworking.observe(this, {
            Toast.makeText(applicationContext, R.string.networking_error, Toast.LENGTH_LONG).show()
        })
        viewModel.generalException.observe(this, {
            Toast.makeText(applicationContext, R.string.general_error, Toast.LENGTH_LONG).show()
        })
    }
}