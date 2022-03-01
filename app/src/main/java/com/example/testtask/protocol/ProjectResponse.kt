package com.example.testtask.protocol

import com.google.gson.annotations.SerializedName

data class ProjectResponse (
    @SerializedName("message") var message: String? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: ArrayList<ProjectData> = arrayListOf()
)

data class ProjectData (
    @SerializedName("id") var id: String? = null,
    @SerializedName("statusDesc") var statusDesc: String? = null,
    @SerializedName("nameRus") var nameRus: String? = null,
    @SerializedName("head") var head: String? = null,
    @SerializedName("directionHead") var directionHead: String? = null,
    @SerializedName("typeDesc") var typeDesc: String? = null,
    @SerializedName("vacancies") var vacancies: String? = null,
    @SerializedName("number") var number: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
)