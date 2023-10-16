package com.chefgo.data.repository

import com.chefgo.data.datasource.remote.mapper.RecipeMapper
import com.chefgo.data.datasource.remote.source.RemoteRecipeDataSource
import com.chefgo.domain.model.Recipe
import com.chefgo.domain.repository.RecipeRepository

class RecipeRepositoryImp(
    private val remoteRecipeDataSource: RemoteRecipeDataSource,
    private val recipeMapper: RecipeMapper
) :
    RecipeRepository {

    override suspend fun getRecipes(): ArrayList<Recipe> {
        return recipeMapper.mapper(remoteRecipeDataSource.getRecipes())
    }

}