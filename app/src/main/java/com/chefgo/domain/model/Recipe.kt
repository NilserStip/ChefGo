package com.chefgo.domain.model

data class Recipe(

    val id: String,
    val logo: String,
    val name: String,
    val description: String,
    val ingredients: String,
    val preparation: String,
    val people: Int,
    val location: String

)
