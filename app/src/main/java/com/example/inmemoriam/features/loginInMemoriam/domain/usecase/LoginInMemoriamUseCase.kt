package com.example.inmemoriam.features.loginInMemoriam.domain.usecase

import com.example.inmemoriam.features.loginInMemoriam.domain.model.LoginInMemoriamModel
import com.example.inmemoriam.features.loginInMemoriam.domain.repository.ILoginInMemoriamRepository

class LoginInMemoriamUseCase(
    private val repository: ILoginInMemoriamRepository
) {
    suspend fun login(username: String, password: String): LoginInMemoriamModel {
        return repository.login(username, password)
    }

    suspend fun contactSupport(): Boolean {
        return repository.contactSupport()
    }

    suspend fun recoverPassword(username: String): Boolean {
        return repository.recoverPassword(username)
    }
}
