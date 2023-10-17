package com.chefgo.domain.usecase

import com.chefgo.data.repository.RecipeRepositoryImp
import com.chefgo.domain.model.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepositoryImp: RecipeRepositoryImp) {
    suspend operator fun invoke(): List<Recipe> {
        return recipeRepositoryImp.getRecipes()
    }
}