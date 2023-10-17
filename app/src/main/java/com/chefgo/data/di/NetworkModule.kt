package com.chefgo.data.di

import com.chefgo.BuildConfig
import com.chefgo.data.datasource.remote.api.ApiConstants
import com.chefgo.data.datasource.remote.api.ApiClient
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    private var retrofit: Retrofit? = null

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .readTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(ClientInterceptor())
            .addInterceptor(interceptor)

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
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