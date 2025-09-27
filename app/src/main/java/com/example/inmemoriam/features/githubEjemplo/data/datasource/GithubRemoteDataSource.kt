package com.example.inmemoriam.features.githubEjemplo.data.datasource

import com.example.inmemoriam.features.githubEjemplo.data.api.GithubService
import com.example.inmemoriam.features.githubEjemplo.data.error.DataException
import com.example.inmemoriam.features.githubEjemplo.domain.model.UrlPath
import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel

class GithubRemoteDataSource(
    val githubService: GithubService
) {
    suspend fun getUser(nick: String): Result<UserModel> {
        val response = githubService.getInfoAvatar(nick)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                try {
                    return Result.success(UserModel(
                        body.login,
                        UrlPath(body.url)
                    ))
                } catch (e: Exception) {
                    return Result.failure(
                        DataException.Unknown(
                            e.message.toString()))
                }

            } else {
                return Result.failure(DataException.NoContent)
            }
        } else if (response.code() == 404) {
            return Result.failure(DataException.HttpNotFound)
        } else {
            return Result.failure(DataException.Unknown(response.message()))
        }
    }
}