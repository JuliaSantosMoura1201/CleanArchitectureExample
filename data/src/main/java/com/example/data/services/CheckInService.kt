package com.example.data.services

import com.example.data.models.CheckInRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInService {

    @POST("/checkin")
    suspend fun makeCheckIn(
        @Body requestBody: CheckInRequestBody
    ): Response<Void>
}