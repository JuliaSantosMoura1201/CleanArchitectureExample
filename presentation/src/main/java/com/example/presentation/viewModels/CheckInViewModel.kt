package com.example.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.exceptions.CheckInException
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.usecases.CheckInUseCase
import com.example.presentation.R
import com.example.presentation.util.StringLoader
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val checkInUseCase: CheckInUseCase,
    private val stringLoader: StringLoader
): ViewModel(){

    private val _success = MutableLiveData<String>()
    val success : LiveData<String> = _success

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun makeCheckIn(
        email: String?,
        eventId: String?,
        name: String?
    ){
        viewModelScope.launch {
            when(val result = checkInUseCase.execute(
                CheckInUseCase.Params(
                    email = email,
                    eventId = eventId,
                    name = name
                )
            )){
              is Either.Success -> _success.postValue(stringLoader.get(R.string.success_message))
              is Either.Failure -> when( result.cause ){
                  is CheckInException -> handlerFormException(result.cause as CheckInException)
                  is NoNetworkingException -> _error.postValue(stringLoader.get(R.string.networking_error))
                  else -> _error.postValue(stringLoader.get(R.string.general_error))
              }
            }
        }
    }

    private fun handlerFormException(exception: CheckInException){
        _error.postValue(
            stringLoader.get(
                when(exception){
                    CheckInException.EmptyEmailException -> R.string.empty_email_message
                    CheckInException.EmptyNameException -> R.string.empty_name_message
                    CheckInException.InvalidEmailException -> R.string.invalid_email_message
                    CheckInException.InvalidEventId -> R.string.invalid_id_message
                }
            )
        )
    }
}