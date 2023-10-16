package com.chefgo.data.datasource.remote.api

import com.chefgo.BuildConfig

class ApiConstants {

    companion object {

        /* -------------------------------------- CHEF GO ---------------------------------------- */

        const val APPLICATION_JSON = "application/json"
        const val BASE_URL = BuildConfig.BASE_URL
        const val TIME_OUT = 30L
        const val ANDROID = "Android"

        // HEADER NAMES
        const val H_CONTENT_TYPE = "Content-Type"
        const val H_ACCEPT = "Accept"
        const val H_PLATFORM = "Platform"
        const val H_VERSION = "Version"

        // END POINTS
        const val EP_RECIPES_LIST = "recipes/list"
    }
}