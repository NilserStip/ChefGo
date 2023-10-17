package com.chefgo.data.datasource.remote.api

import com.chefgo.data.datasource.remote.api.response.RecipeResponse
import retrofit2.http.*

interface ApiClient {

    /* -------------------------------------- CHEF GO ---------------------------------------- */

    @GET(ApiConstants.EP_RECIPES_LIST)
    suspend fun recipeList(): List<RecipeResponse>

 }