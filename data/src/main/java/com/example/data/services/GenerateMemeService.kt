package com.example.data.services

import com.example.data.models.GenerateMemeResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenerateMemeService {

    @GET("/meme")
    suspend fun generateMeme(
        @Query("meme") meme: String,
        @Query("bottom") bottom: String,
        @Query("font") font: String,
        @Query("font_size") fontSize: String
    ): Response<GenerateMemeResponseBody>
}