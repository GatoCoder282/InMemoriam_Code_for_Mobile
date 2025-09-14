package com.example.inmemoriam.features.loginejemplo.domain.repository

import com.example.inmemoriam.features.loginejemplo.domain.model.LoginEjemploModel

interface ILoginEjemploRepository {
    suspend fun login(email: String, password: String): Result<LoginEjemploModel>
    suspend fun recover(email: String): Result<Unit>
}