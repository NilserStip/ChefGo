package com.chefgo.data.datasource.remote.source

import com.chefgo.data.datasource.ChefGoProvider
import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import com.chefgo.data.di.ChefGoService
import javax.inject.Inject

class RemoteRecipeDataSource @Inject constructor(private val chefGoService: ChefGoService, private val chefGoProvider: ChefGoProvider) {

    suspend fun getRecipes(): List<RecipeResponse> {
        val response = chefGoService.getRecipes()
        chefGoProvider.recipes = response
        return response
    }
}