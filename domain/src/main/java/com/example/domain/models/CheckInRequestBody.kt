package com.example.domain.models

data class CheckInRequestBody (
    val email: String,
    val name: String,
    val eventId: String
    )