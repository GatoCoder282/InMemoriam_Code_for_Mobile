package com.example.inmemoriam.features.githubEjemplo.domain.repository

import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel

interface IGithubRepository {
     suspend fun findByNick(value: String) : Result<UserModel>
}