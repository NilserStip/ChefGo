package com.chefgo.data

import com.chefgo.BuildConfig
import com.chefgo.data.datasource.remote.api.ApiServices
import com.chefgo.data.datasource.remote.api.ApiConstants
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChefGoClient {

    companion object {
        private var retrofit: Retrofit? = null

        init {
            val httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .readTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(ClientInterceptor())
                .addInterceptor(provideLoggingInterceptor())

            retrofit = Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }

    }

    fun get(): ApiServices {
        return retrofit!!.create(ApiServices::class.java)
    }

    class ClientInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader(ApiConstants.H_PLATFORM, ApiConstants.ANDROID)
                .addHeader(ApiConstants.H_VERSION, BuildConfig.VERSION_NAME)
                .addHeader(ApiConstants.H_CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .addHeader(ApiConstants.H_ACCEPT, ApiConstants.APPLICATION_JSON)
                .build()
            val response = chain.proceed(request)

            if (response.isSuccessful && response.body != null) {
                return response.newBuilder()
                    .body(response.body!!.string().toResponseBody(response.body!!.contentType()!!))
                    .build()
            }

            return response
        }

    }

}