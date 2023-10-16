package com.chefgo.presentation.feature.recipe.viewmodel

interface Factory<T> {
    fun create(): T
}