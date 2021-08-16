package com.example.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.exceptions.CheckInException
import com.example.domain.exceptions.NoNetworkingException
import com.example.presentation.R
import kotlinx.coroutines.launch

