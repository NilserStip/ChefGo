package com.chefgo.data.datasource.remote.mapper

import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import com.chefgo.domain.model.Recipe

class RecipeMapper {

    fun mapper(data: List<RecipeResponse>): ArrayList<Recipe> {
        val array = ArrayList<Recipe>()
        if (data.isEmpty()) return array

        data.forEach {
            array.add(Recipe(it.id, it.logo, it.name, it.description, it.location))
        }
        return array
    }

}