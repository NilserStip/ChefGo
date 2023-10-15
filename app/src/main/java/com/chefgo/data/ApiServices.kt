package com.chefgo.data

import com.chefgo.data.model.ApiConstants
import com.chefgo.domain.RecipeResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    /* -------------------------------------- CHEF GO ---------------------------------------- */

    @GET(ApiConstants.EP_RECIPES_LIST)
    fun recipeList(): Call<ArrayList<RecipeResponse>>

//    @POST(ApiConstants.EP_USER_VALIDATE_SOCIAL)
//    fun validateUserSocial(
//        @Body body: ValidateUserSocialRequest
//    ): Call<String>

 }