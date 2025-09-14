package com.example.inmemoriam.features.loginejemplo.data.repository

import com.example.inmemoriam.features.loginejemplo.domain.model.LoginEjemploModel
import com.example.inmemoriam.features.loginejemplo.domain.repository.ILoginEjemploRepository
import kotlinx.coroutines.delay
import java.util.UUID

class LoginEjemploRepository : ILoginEjemploRepository {
    override suspend fun login(email: String, password: String): Result<LoginEjemploModel> {
        delay(1200) // simular red
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Completa email y contraseña"))
        }
        if (password.length < 4) {
            return Result.failure(IllegalArgumentException("Contraseña demasiado corta"))
        }
        return Result.success(
            LoginEjemploModel(
                id = UUID.randomUUID().toString(),
                name = "Usuario Demo",
                email = email,
                token = "token-demo"
            )
        )
    }

    override suspend fun recover(email: String): Result<Unit> {
        delay(800)
        return if (email.contains("@")) Result.success(Unit)
        else Result.failure(IllegalArgumentException("Email inválido"))
    }
}