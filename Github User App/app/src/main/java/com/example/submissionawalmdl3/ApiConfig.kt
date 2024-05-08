package com.example.submissionawalmdl3

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiConfig {
    companion object{

        private const val BASE_URL = "https://api.github.com/"
        private const val secretKey = BuildConfig.KEY
        fun getApiService(): ApiInterface{

            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val reqHeaders = req.newBuilder()
                    .addHeader("Authorization", secretKey)
                    .build()
                chain.proceed(reqHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}