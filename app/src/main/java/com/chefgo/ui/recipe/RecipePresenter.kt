package com.chefgo.ui.recipe

import com.chefgo.base.BasePresenter
import com.chefgo.data.ChefGoClient
import com.chefgo.domain.RecipeResponse
import com.chefgo.ui.model.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipePresenter : BasePresenter<RecipeView>() {

    private lateinit var view: RecipeView

    override fun addView(view: RecipeView) {
        this.view = view
    }

    fun getRecipes() {
        view.showLoading()
        ChefGoClient.getInstance(view).get().recipeList()
            .enqueue(object : Callback<ArrayList<RecipeResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<RecipeResponse>>,
                    response: Response<ArrayList<RecipeResponse>>
                ) {
                    view.hideLoading()

                    if (response.isSuccessful)
                        view.getRecipesSuccess(mapper(response.body()!!))
                    else
                        view.getRecipesFailure()
                }


                override fun onFailure(
                    call: Call<ArrayList<RecipeResponse>>,
                    t: Throwable
                ) {
                    view.onFailure(
                        RecipePresenter::class.java.simpleName,
                        javaClass.enclosingMethod!!.name,
                        t
                    )
                    view.hideLoading()
                    view.getRecipesFailure()
                }
            })
    }

    private fun mapper(data: ArrayList<RecipeResponse>): ArrayList<Recipe> {
        val array = ArrayList<Recipe>()
        if (data.isEmpty()) return array

        data.forEach {
            array.add(Recipe(it.id, it.logo, it.name, it.description, it.location))
        }
        return array
    }


}