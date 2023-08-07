package com.example.iqsectest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ClientRetrofit {
    private val httpClient = OkHttpClient.Builder()
    lateinit var client: OkHttpClient

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        client = httpClient.build()

        return Retrofit.Builder()
            .baseUrl("https://servicesoap.azurewebsites.net/ws/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
    }
}