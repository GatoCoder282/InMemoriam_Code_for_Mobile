package com.example.inmemoriam.features.loginejemplo.domain.usecase

import com.example.inmemoriam.features.loginejemplo.domain.model.LoginEjemploModel
import com.example.inmemoriam.features.loginejemplo.domain.repository.ILoginEjemploRepository

class LoginEjemploUseCase(
    private val repository: ILoginEjemploRepository
) {
    suspend fun invoke(email: String, password: String): Result<LoginEjemploModel> =
        repository.login(email.trim(), password)
}