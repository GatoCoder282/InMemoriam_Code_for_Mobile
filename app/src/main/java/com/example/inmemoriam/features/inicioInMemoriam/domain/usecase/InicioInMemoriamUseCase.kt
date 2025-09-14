package com.example.inmemoriam.features.inicioInMemoriam.domain.usecase

import com.example.inmemoriam.features.inicioInMemoriam.domain.model.InicioInMemoriamModel
import com.example.inmemoriam.features.inicioInMemoriam.domain.repository.IInicioInMemoriamRepository

class InicioInMemoriamUseCase(
    private val repository: IInicioInMemoriamRepository
) {
    suspend fun login(): InicioInMemoriamModel {
        return repository.login()
    }

    suspend fun joinAsGuest(): InicioInMemoriamModel {
        return repository.joinAsGuest()
    }
}