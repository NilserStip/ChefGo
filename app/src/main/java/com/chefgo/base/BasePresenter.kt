package com.chefgo.base

import android.content.Context
import com.chefgo.domain.ErrorBody
import com.chefgo.domain.ErrorMessage
import com.chefgo.domain.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BasePresenter<T>(private val context: Context? = null) : BasePresenterView<T> {

    open fun getMessage(response: Response<*>): String? {
        val error = response.errorBody().toString()
        val (message) = Gson().fromJson(error, ErrorMessage::class.java)
        return message
    }

    open fun getMessageError(response: Response<*>): String? {
        return getErrorResponse(response).message
    }

    open fun getErrorResponse(response: Response<*>): ErrorResponse {
        val error = response.errorBody().toString()
        val (error1) = Gson().fromJson(error, ErrorBody::class.java)
        return error1
//        try {
//            JSONObject jsonObject = new JSONObject(messageBody);
//            error = jsonObject.getJSONObject("error");
//            messageError = error.getString("message");
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
    }

}