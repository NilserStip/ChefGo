package com.chefgo

import com.chefgo.data.ChefGoClient
import com.chefgo.data.datasource.remote.mapper.RecipeMapper
import com.chefgo.data.datasource.remote.source.RemoteRecipeDataSource
import com.chefgo.data.repository.RecipeRepositoryImp
import com.chefgo.domain.usecase.GetRecipesUseCase
import com.chefgo.presentation.feature.recipe.viewmodel.RecipeContainer

class AppContainer {

    private val client = ChefGoClient()
    private val remoteRecipeDataSource = RemoteRecipeDataSource(client)
    private val recipeMapper = RecipeMapper()
    private val recipeRepository = RecipeRepositoryImp(remoteRecipeDataSource, recipeMapper)

    val getRecipesUseCase = GetRecipesUseCase(recipeRepository)

    var recipeContainer: RecipeContainer? = null
}