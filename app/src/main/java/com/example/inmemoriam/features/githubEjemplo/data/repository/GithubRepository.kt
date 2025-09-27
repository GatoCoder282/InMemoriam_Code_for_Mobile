package com.example.inmemoriam.features.githubEjemplo.data.repository

import com.example.inmemoriam.features.githubEjemplo.data.datasource.GithubRemoteDataSource
import com.example.inmemoriam.features.githubEjemplo.data.error.DataException
import com.example.inmemoriam.features.githubEjemplo.domain.error.Failure
import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IGithubRepository

class GithubRepository(
    val remoteDataSource: GithubRemoteDataSource
): IGithubRepository {
    override suspend fun findByNick(value: String): Result<UserModel> {
        if(value.isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        val response = remoteDataSource.getUser(value)

        response.fold(
            onSuccess = {
                return Result.success(it)
            },
            onFailure = { exception ->
                val failure = when (exception) {
                    is DataException.Network -> Failure.NetworkConnection
                    is DataException.HttpNotFound -> Failure.NotFound
                    is DataException.NoContent -> Failure.EmptyBody
                    is DataException.Unknown -> Failure.Unknown(exception)
                    else -> Failure.Unknown(exception)
                }
                return Result.failure(failure)
            }
        )
    }
}