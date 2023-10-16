package com.chefgo.data.datasource.remote.source

import com.chefgo.data.ChefGoClient
import com.chefgo.data.datasource.remote.api.response.RecipeResponse

class RemoteRecipeDataSource(private val client: ChefGoClient) {

    suspend fun getRecipes(): List<RecipeResponse>{
        return client.get().recipeList()
    }
}