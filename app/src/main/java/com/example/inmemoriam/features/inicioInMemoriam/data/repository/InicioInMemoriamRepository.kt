package com.example.inmemoriam.features.inicioInMemoriam.data.repository

import com.example.inmemoriam.features.inicioInMemoriam.domain.model.InicioInMemoriamModel
import com.example.inmemoriam.features.inicioInMemoriam.domain.repository.IInicioInMemoriamRepository

class InicioInMemoriamRepository : IInicioInMemoriamRepository {

    override suspend fun login(): InicioInMemoriamModel {
        // Simulación de login exitoso
        return InicioInMemoriamModel(
            isLoggedIn = true,
            isGuest = false
        )
    }

    override suspend fun joinAsGuest(): InicioInMemoriamModel {
        // Simulación de acceso como invitado
        return InicioInMemoriamModel(
            isLoggedIn = false,
            isGuest = true
        )
    }
}
