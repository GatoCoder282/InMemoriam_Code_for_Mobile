package com.example.inmemoriam.features.loginejemplo.domain.usecase

import com.example.inmemoriam.features.loginejemplo.domain.repository.ILoginEjemploRepository


class RecoverPasswordEjemploUsecase(private val repository: ILoginEjemploRepository) {
    suspend fun invoke(email: String): Result<Unit> =
        repository.recover(email.trim())
}