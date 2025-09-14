package com.example.inmemoriam.features.guestInMemoriam.data.repository

import com.example.inmemoriam.features.guestInMemoriam.domain.model.GuestInMemoriamModel
import com.example.inmemoriam.features.guestInMemoriam.domain.repository.IGuestInMemoriamRepository

class GuestInMemoriamRepository : IGuestInMemoriamRepository {

    override suspend fun searchByCode(code: String): GuestInMemoriamModel {
        return if (code == "INM123") {
            GuestInMemoriamModel(
                searchedCode = code,
                result = "Record found: María Fernanda López"
            )
        } else {
            GuestInMemoriamModel(
                searchedCode = code,
                errorMessage = "No record found for code: $code"
            )
        }
    }

    override suspend fun processQr(qrContent: String): GuestInMemoriamModel {
        return if (qrContent.startsWith("QR-INM")) {
            GuestInMemoriamModel(
                scannedCode = qrContent,
                result = "QR linked to: José Antonio Rivera"
            )
        } else {
            GuestInMemoriamModel(
                scannedCode = qrContent,
                errorMessage = "Invalid QR format"
            )
        }
    }
}
