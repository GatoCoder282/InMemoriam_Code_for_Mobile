package com.example.inmemoriam.domain.respository

import com.example.inmemoriam.domain.model.UserModel

interface IdGitHubRepository {
    fun findByNick(value: String) : Result<UserModel>
}