package com.example.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.exceptions.CheckInException
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.usecases.CheckInUseCase
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val checkInUseCase: CheckInUseCase
): ViewModel(){

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success

    private val _checkInException = MutableLiveData<CheckInException>()
    val checkInException: LiveData<CheckInException> = _checkInException

    private val _generalException = MutableLiveData<Unit>()
    val generalException: LiveData<Unit> = _generalException

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun makeCheckIn(
        email: String?,
        name: String?,
        eventId: String?
    ){
        viewModelScope.launch {
            when(val result = checkInUseCase.execute(
                CheckInUseCase.Params(
                    email = email,
                    eventId =eventId,
                    name = name
                )
            )){
                is Either.Success -> _success.postValue(Unit)
                is Either.Failure -> when(result.cause){
                    is CheckInException -> _checkInException.postValue(result.cause as CheckInException)
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _generalException.postValue(Unit)
                }
            }
        }
    }

}