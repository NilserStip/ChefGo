package com.chefgo.data

import com.chefgo.BuildConfig

class ApiConstants {

    companion object {


        /* -------------------------------------- CHEF GO ---------------------------------------- */

        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
        const val APPLICATION_JSON = "application/json"
        const val BASE_URL = BuildConfig.BASE_URL
        const val TIME_OUT = 30L
        const val ANDROID = "Android"

        // HEADER NAMES
        const val X_API_KEY = "X-ApiKey"
        const val H_AUTHORIZATION = "Authorization"
        const val H_CONTENT_TYPE = "Content-Type"
        const val H_ACCEPT = "Accept"
        const val H_PLATFORM = "Platform"
        const val H_VERSION = "Version"


        // END POINTS

        const val EP_RECIPES_LIST = "recipes/list"
        const val EP_APP_INFO = "app/info"
        const val EP_APP_FREQUENT_QUESTIONS = "app/frequentQuestions"

        const val EP_USER_VALIDATE_SOCIAL = "user/validate/socialMedia"
        const val EP_USER_VALIDATE_PHONE = "user/validate/phone"
        const val EP_USER_VALIDATE = "user/validate"
        const val EP_USER_LOGIN = "user/login"
        const val EP_USER_REGISTER = "user/register"
        const val EP_USER_SUBSCRIPTIONS = "user/subscriptions"
        const val EP_USER_FORGOT_PASSWORD_EMAIL = "user/forgotPassword/email"
        const val EP_USER_CHANGE_EMAIL = "user/changeEmail"
        const val EP_USER_CHANGE_PASSWORD = "user/changePassword"
        const val EP_USER_CHANGE_FULL_NAME = "user/changeFullName"

        const val EP_PLATFORMS_SECTIONS = "platforms/sections"
        const val EP_PLATFORMS_ACCOUNTS = "platforms/accounts"

        // PARAMS
        const val P_NAME = "name"
        const val P_LAST_NAME = "lastName"
        const val P_EMAIL = "email"
        const val P_PHONE = "phone"
        const val P_PASSWORD = "password"
        const val P_TOKEN = "token"
        const val P_FCM_TOKEN = "fcmToken"
        const val P_TYPE = "type"
        const val P_PHOTO_URL = "photoUrl"
        const val P_ID = "id"

        /* -------------------------------------- REST SERVICES ---------------------------------------- */

        // REQUEST
        const val R_ANDROID = "android"
        const val R_FULL_NAME = "full_name"
        const val R_TYPE = "type"
        const val R_BUSINESS_NAME = "business_name"
        const val R_TYPE_DOCUMENT = "type_document"
        const val R_DOCUMENT_NUMBER = "document_number"
        const val R_DISTRICT_ID = "district_id"
        const val R_POINT = "point"
        const val R_REFERENCES = "references"
        const val R_CREDIT_CARD_TOKEN_ID = "creditCardTokenId"
        const val R_NAME = "name"
        const val R_PAYMENT_METHOD = "paymentMethod"
        const val R_MASKED_NUMBER = "maskedNumber"
        const val R_TESTIMONY = "testimony"
        const val R_QUALIFICATION = "qualification"
        const val R_STORE_ID = "store_id"
        const val R_SHOPPING_ID = "shopping_id"

        // RESPONSE
        const val OK = "OK"
        const val UNAUTHENTICATED = 401
        const val VISA = "VISA"
        const val MASTERCARD = "MASTERCARD"
        const val AMEX = "AMEX"
        const val DINERS = "DINERS"
        const val PAGOEFECTIVO = "PAGOEFECTIVO"
        const val PRIORITY = "priority"
        const val APPROVED = "APPROVED"
        const val DECLINED = "DECLINED"
        const val ERROR = "ERROR"
        const val EXPIRED = "EXPIRED"
        const val PENDING = "PENDING"
        const val ON_ROUTE = "ON ROUTE"
        const val DELIVERED = "DELIVERED"
        const val QUALIFIED = "QUALIFIED"

        // CLIENT
        const val PASSWORD_RESET = "client/password/reset"
        const val UPDATE_PROFILE = "client/profile/update"
        const val UPDATE_PHONE = "client/profile/update/phone"
        const val LOGOUT = "client/logout"
        const val ALL_SHOPPING_PROFILE = "client/shopping/profile"
        const val SET_PRIORITY_SHOPPING_PROFILE = "client/shopping/profile/priority/{id}"
        const val REGISTER_SHOPPING_PROFILE = "client/shopping/profile/register"
        const val UPDATE_SHOPPING_PROFILE = "client/shopping/profile/update/{id}"
        const val DELETE_SHOPPING_PROFILE = "client/shopping/profile/delete/{id}"
        const val PAYMENT_METHODS = "client/payment/method"
        const val REGISTER_PAYMENT_METHOD = "client/payment/method/register"
        const val UPDATE_PAYMENT_METHOD = "client/payment/method/update/{id}"
        const val DELETE_PAYMENT_METHOD = "client/payment/method/delete/{id}"
        const val CREATE_TRANSACTION = "client/shopping/create"
        const val SHOPPING = "client/shopping"

        // STORE
        const val ALL_PRODUCTS = "store/products/{id}"
        const val ALL_SERVICES = "print/services/{id}"
        const val ALL_STORE = "store/"

        // PRINT
        const val ALL_PRINT = "print/"
        const val UBIGEO_STORE = "print/filter/ubigeo"
        const val ALL_UBIGEO = "ubigeo/all"

        // TESTIMONY
        const val REGISTER_TESTIMONY = "testimony/register"

        // UBIGEO
        const val UBIGEO_DEPARTMENTS = "ubigeo/departments"
        const val UBIGEO_PROVINCES = "ubigeo/provinces/{id}"
        const val UBIGEO_DISTRICTS = "ubigeo/districts/{id}"

        // ADVERTISING
        const val ADVERTISING = "advertising"


    }
}