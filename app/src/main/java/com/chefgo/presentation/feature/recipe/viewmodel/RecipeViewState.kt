package com.chefgo.presentation.feature.recipe.viewmodel

import com.chefgo.domain.model.Recipe

sealed class RecipeViewState {
    class ShowList(val data: List<Recipe>) : RecipeViewState()
    object ShowError : RecipeViewState()
    object ShowLoading : RecipeViewState()
    object HideLoading : RecipeViewState()
}