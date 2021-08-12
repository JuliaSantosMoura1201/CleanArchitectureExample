package com.example.domain.exceptions

sealed class CheckInException : Exception(){
    object InvalidEventId: CheckInException()
    object EmptyEmailException: CheckInException()
    object InvalidEmailException: CheckInException()
    object EmptyNameException: CheckInException()
}