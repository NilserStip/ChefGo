package com.chefgo.domain.repository

import com.chefgo.domain.model.Recipe

interface RecipeRepository {

    suspend fun getRecipes(): List<Recipe>

}