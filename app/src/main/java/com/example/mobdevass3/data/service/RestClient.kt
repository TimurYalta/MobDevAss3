package com.example.mobdevass3.data.service

import com.example.mobdevass3.data.prefs.Prefs
import com.example.mobdevass3.utils.Consts
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

   private var  instance: ApiService? = null

    fun getInstance(baseUrl: String): ApiService{
        return instance?: create(baseUrl)
    }

    private fun createNetworkClient(isDebug: Boolean = true): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addNetworkInterceptor(createAccessTokenProvidingInterceptor())
            .build()
    }

    private fun create(baseUrl: String = Consts.API_BASE_URL): ApiService {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .setLenient()
            .serializeNulls()
            .create()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(createNetworkClient())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private val accessToken: String
        get() = Prefs.getToken()

    private fun createAccessTokenProvidingInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}