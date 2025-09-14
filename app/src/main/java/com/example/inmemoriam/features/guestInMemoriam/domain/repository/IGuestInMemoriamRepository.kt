package com.example.inmemoriam.features.guestInMemoriam.domain.repository

import com.example.inmemoriam.features.guestInMemoriam.domain.model.GuestInMemoriamModel

interface IGuestInMemoriamRepository {
    suspend fun searchByCode(code: String): GuestInMemoriamModel
    suspend fun processQr(qrContent: String): GuestInMemoriamModel
}
