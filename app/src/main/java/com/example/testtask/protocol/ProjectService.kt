package com.example.testtask.protocol

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectService {

    @GET("/public-api/projects")
    fun getProjectCatalog(): Call<ProjectResponse>

    @GET("/public-api/project/bynumber/header/{projectNum}")
    fun getProjectInfo(@Path("projectNum") projectNumb: String): Call<ProjectInfoResponse>
}

fun getThumbnailURL(projectId: String) = "https://cabinet.miem.hse.ru/project/thumbnail/$projectId"

