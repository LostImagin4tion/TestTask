package com.example.testtask.protocol

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API(private val baseURL: String) {

    fun connectUserService(): UserService {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    fun connectProjectService(): ProjectService {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProjectService::class.java)
    }
}