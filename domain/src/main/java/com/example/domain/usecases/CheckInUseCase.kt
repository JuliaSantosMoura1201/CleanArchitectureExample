package com.example.domain.usecases

import com.example.domain.Either
import com.example.domain.exceptions.CheckInException
import com.example.domain.isValidEmail
import com.example.domain.models.CheckInRequestBody
import com.example.domain.repositories.CheckInRepository

class CheckInUseCase (
    private val checkInRepository: CheckInRepository
    ){

    data class Params(
        val email: String?,
        val eventId: String?,
        val name: String?
    )

    suspend fun execute(params: Params?): Either<Unit, Exception>{
        if(params == null) throw IllegalArgumentException()
        return when{
            params.name.isNullOrEmpty() -> Either.Failure(CheckInException.EmptyNameException)
            params.email.isNullOrEmpty() -> Either.Failure(CheckInException.EmptyEmailException)
            !params.email.isValidEmail() -> Either.Failure(CheckInException.InvalidEmailException)
            params.eventId.isNullOrEmpty() -> Either.Failure(CheckInException.InvalidEventId)
            else -> checkInRepository.makeCheckIn(
                    CheckInRequestBody(
                        email = params.email,
                        eventId = params.eventId,
                        name = params.name
                    )
            )
        }
    }
}