package com.example.inmemoriam.features.guestInMemoriam.domain.model

data class GuestInMemoriamModel(
    val isGuest: Boolean = true,
    val scannedCode: String? = null,
    val searchedCode: String? = null,
    val result: String? = null,
    val errorMessage: String? = null
)
