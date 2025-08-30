package com.example.inmemoriam.features.githubEjemplo.domain.repository

import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel

interface IdGitHubRepository {
    fun findByNick(value: String) : Result<UserModel>
}