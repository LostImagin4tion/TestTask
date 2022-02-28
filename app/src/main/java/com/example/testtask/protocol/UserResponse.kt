package com.example.testtask.protocol

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data?   = Data()
)

data class Data (
    @SerializedName("userId") var userId: Int? = null,
    @SerializedName("fullName") var fullName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("emailsList") var emailsList: ArrayList<String> = arrayListOf(),
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("isTeacher") var isTeacher: Boolean?  = null,
    @SerializedName("trelloId") var trelloId: String? = null
)