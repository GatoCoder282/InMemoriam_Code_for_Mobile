package com.example.inmemoriam.features.loginInMemoriam.domain.model

data class LoginInMemoriamModel(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)
