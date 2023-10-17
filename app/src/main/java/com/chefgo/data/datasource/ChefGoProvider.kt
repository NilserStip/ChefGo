package com.chefgo.data.datasource

import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import com.chefgo.domain.model.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChefGoProvider @Inject constructor() {
    var recipes: List<RecipeResponse> = emptyList()
}