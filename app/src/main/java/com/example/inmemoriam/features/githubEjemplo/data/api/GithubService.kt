package com.example.inmemoriam.features.githubEjemplo.data.api

import com.example.inmemoriam.features.githubEjemplo.data.api.dto.GithubDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{githubLogin}")
    suspend fun getInfoAvatar(@Path("githubLogin") githubLogin: String): Response<GithubDto>
}