package com.example.inmemoriam.features.profile.domain.model

@JvmInline
value class Name(val value: String) {
    init {
        require(value.isNotBlank()) { "El nombre no puede estar vacío" }
    }
}

@JvmInline
value class Email(val value: String) {
    init {
        require(value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) { "Email inválido" }
    }
}

@JvmInline
value class Cellphone(val value: String) {
    init {
        require(value.isNotBlank()) { "El celular no puede estar vacío" }
    }
}

@JvmInline
value class Summary(val value: String)

@JvmInline
value class PathUrl(val value: String)

data class ProfileModel(
    val pathUrl: PathUrl,
    val name: Name,
    val email: Email,
    val cellphone: Cellphone,
    val summary: Summary
)
