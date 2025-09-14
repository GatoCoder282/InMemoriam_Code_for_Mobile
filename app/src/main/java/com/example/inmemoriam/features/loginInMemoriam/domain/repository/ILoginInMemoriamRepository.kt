package com.example.inmemoriam.features.loginInMemoriam.domain.repository

import com.example.inmemoriam.features.loginInMemoriam.domain.model.LoginInMemoriamModel

interface ILoginInMemoriamRepository {
    suspend fun login(username: String, password: String): LoginInMemoriamModel
    suspend fun contactSupport(): Boolean
    suspend fun recoverPassword(username: String): Boolean
}
