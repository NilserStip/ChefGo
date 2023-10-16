package com.chefgo.domain.usecase

import com.chefgo.domain.repository.RecipeRepository

class GetRecipesUseCase constructor(private val recipeRepository: RecipeRepository) {

    suspend operator fun invoke() = recipeRepository.getRecipes()
}