package com.chefgo.ui.recipe

import com.chefgo.base.BaseView
import com.chefgo.ui.model.Recipe

interface RecipeView : BaseView {

    fun showLoading()

    fun hideLoading()

    fun getRecipesSuccess(data: ArrayList<Recipe>)

    fun getRecipesFailure()

}