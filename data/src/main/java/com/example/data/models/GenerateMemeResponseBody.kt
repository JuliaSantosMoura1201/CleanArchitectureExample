package com.example.data.models

data class CheckInRequestBody(
    val email: String,
    val eventId: String,
    val name: String
)