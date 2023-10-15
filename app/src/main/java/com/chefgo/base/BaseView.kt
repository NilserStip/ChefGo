package com.chefgo.base

interface BaseView {

    fun showViewMessage(message: String? = null)

    fun onFailure(className: String, methodName: String, t: Throwable)

}