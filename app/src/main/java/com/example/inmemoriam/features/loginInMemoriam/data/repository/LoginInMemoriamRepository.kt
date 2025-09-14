package com.example.inmemoriam.features.loginInMemoriam.data.repository

import com.example.inmemoriam.features.loginInMemoriam.domain.model.LoginInMemoriamModel
import com.example.inmemoriam.features.loginInMemoriam.domain.repository.ILoginInMemoriamRepository

class LoginInMemoriamRepository : ILoginInMemoriamRepository {

    override suspend fun login(username: String, password: String): LoginInMemoriamModel {
        return if (username == "admin" && password == "1234") {
            LoginInMemoriamModel(
                username = username,
                password = password,
                isLoggedIn = true
            )
        } else {
            LoginInMemoriamModel(
                username = username,
                password = password,
                isLoggedIn = false,
                errorMessage = "Invalid credentials"
            )
        }
    }

    override suspend fun contactSupport(): Boolean {
        // Simulación de contacto exitoso
        return true
    }

    override suspend fun recoverPassword(username: String): Boolean {
        // Simulación de recuperación exitosa si el usuario existe
        return username.isNotBlank()
    }
}
