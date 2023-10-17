package com.chefgo.data.di

import com.chefgo.data.datasource.remote.api.ApiClient
import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import javax.inject.Inject

class ChefGoService @Inject constructor(private val apiClient: ApiClient) {
    suspend fun getRecipes(): List<RecipeResponse> {
        return apiClient.recipeList()
    }
}