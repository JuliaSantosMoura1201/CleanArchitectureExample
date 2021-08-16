package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CheckInRequestBody(

    @SerializedName("email")
    val email: String,

    @SerializedName("eventId")
    val eventId: String,

    @SerializedName("name")
    val name: String
)