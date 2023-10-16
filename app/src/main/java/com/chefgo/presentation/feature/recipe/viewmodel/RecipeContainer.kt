package com.chefgo.presentation.feature.recipe.viewmodel

import com.chefgo.domain.usecase.GetRecipesUseCase

class RecipeContainer(private val getRecipesUseCase: GetRecipesUseCase) {
    val recipeViewModelFactory = RecipeViewModelFactory(getRecipesUseCase)

}