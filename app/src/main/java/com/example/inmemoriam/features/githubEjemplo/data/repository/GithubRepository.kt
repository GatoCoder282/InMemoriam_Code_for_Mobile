package com.example.inmemoriam.features.githubEjemplo.data.repository

import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IdGitHubRepository

class GithubRepository: IdGitHubRepository {
    override fun findByNick(value: String): Result<UserModel> {
        if(value.isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        return Result.success(
            UserModel(
                "calyr",
                "https://avatars.githubusercontent.com/u/874321?v=4"
            )
        )
    }
}