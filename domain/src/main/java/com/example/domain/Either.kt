package com.example.domain

sealed class Either<out A, out B> {

    data class Success<out A>(
        val data: A
    ) : Either<A, Nothing>()

    data class Failure<out B>(
        val cause: B
    ) : Either<Nothing, B>()
}