package com.example.data.repositoryimpl

import com.example.data.mappers.toDataCheckInRequestBody
import com.example.data.services.CheckInService
import com.example.domain.Either
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.models.CheckInRequestBody
import com.example.domain.repositories.CheckInRepository
import java.net.UnknownHostException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckInRepositoryImpl(
    private val service: CheckInService
): CheckInRepository{

    override suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception> =
        withContext( Dispatchers.IO) {
            try{
                val response = service.makeCheckIn(
                    requestBody = checkInRequestBody.toDataCheckInRequestBody()
                )
                when(response.code()){
                    CODE_SUCCESS -> Either.Success(Unit)
                    else -> Either.Failure(Exception())
                }
            } catch (e: UnknownHostException){
                Either.Failure(NoNetworkingException)
            }
        }

    private companion object{
        private const val CODE_SUCCESS = 200
    }
}
