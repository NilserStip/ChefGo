package com.chefgo.domain

import java.io.Serializable

data class ErrorBody(

    val error: ErrorResponse

) : Serializable

data class ErrorResponse(

    val message: String,
    val code: Int

) : Serializable

data class ErrorMessage(

    val message: String

) : Serializable