package com.chefgo.domain

data class RecipeResponse(

    val id: String,
    val logo: String,
    val name: String,
    val description: String,
    val location: String

) : java.io.Serializable
