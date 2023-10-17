package com.chefgo.data.datasource.remote.mapper

import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import com.chefgo.domain.model.Recipe
import javax.inject.Inject

class RecipeMapper @Inject constructor() {

    fun mapper(data: List<RecipeResponse>): ArrayList<Recipe> {
        val array = ArrayList<Recipe>()
        if (data.isEmpty()) return array

        data.forEach {
            array.add(
                Recipe(
                    id = it.id,
                    logo = it.logo,
                    name = it.name,
                    description = it.description,
                    ingredients = it.ingredients,
                    preparation = it.preparation,
                    people = it.people,
                    active = it.active,
                    location = it.location
                )
            )
        }
        return array
    }

}