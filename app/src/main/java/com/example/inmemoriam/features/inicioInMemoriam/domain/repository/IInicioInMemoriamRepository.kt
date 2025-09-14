package com.example.inmemoriam.features.inicioInMemoriam.domain.repository

import com.example.inmemoriam.features.inicioInMemoriam.domain.model.InicioInMemoriamModel

interface IInicioInMemoriamRepository {
    suspend fun login(): InicioInMemoriamModel
    suspend fun joinAsGuest(): InicioInMemoriamModel
}