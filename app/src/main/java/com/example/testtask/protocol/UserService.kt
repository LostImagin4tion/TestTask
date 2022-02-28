package com.example.testtask.protocol

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface UserService {

    @GET("/public-api/user/email/{email}")
    fun getUserInfo(@Path("email") email: String): Call<UserResponse>
}

fun getUserAvatarURL(baseURL: String, id: String) = "${baseURL}public-api/user/$id/avatar"
