package com.chefgo.data.datasource.remote.api.response

data class RecipeResponse(

    val id: String,
    val logo: String,
    val name: String,
    val description: String,
    val ingredients: String,
    val preparation: String,
    val people: Int,
    val active: Boolean,
    val location: String

) : java.io.Serializable
