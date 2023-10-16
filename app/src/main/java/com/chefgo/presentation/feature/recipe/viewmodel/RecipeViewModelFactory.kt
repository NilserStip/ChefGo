package com.chefgo.presentation.feature.recipe.viewmodel

import com.chefgo.domain.usecase.GetRecipesUseCase

class RecipeViewModelFactory(private val getRecipesUseCase: GetRecipesUseCase) : Factory<RecipeViewModel> {

    override fun create(): RecipeViewModel {
        return RecipeViewModel(getRecipesUseCase)
    }

}