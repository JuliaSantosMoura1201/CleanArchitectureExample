package com.example.domain.repositories

import com.example.domain.Either
import com.example.domain.models.CheckInRequestBody

interface CheckInRepository {
    suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception>
}