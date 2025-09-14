package com.example.inmemoriam.features.guestInMemoriam.domain.usecase

import com.example.inmemoriam.features.guestInMemoriam.domain.model.GuestInMemoriamModel
import com.example.inmemoriam.features.guestInMemoriam.domain.repository.IGuestInMemoriamRepository

class GuestInMemoriamUseCase(
    private val repository: IGuestInMemoriamRepository
) {
    suspend fun searchByCode(code: String): GuestInMemoriamModel {
        return repository.searchByCode(code)
    }

    suspend fun processQr(qrContent: String): GuestInMemoriamModel {
        return repository.processQr(qrContent)
    }
}
