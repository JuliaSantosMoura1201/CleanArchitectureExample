package com.example.data.mappers

import com.example.domain.models.CheckInRequestBody


fun CheckInRequestBody.toDataCheckInRequestBody () =
    com.example.data.models.CheckInRequestBody(
        email = email,
        eventId = eventId,
        name = name
    )