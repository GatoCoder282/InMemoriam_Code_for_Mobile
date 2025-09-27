package com.example.inmemoriam.features.dollar.domain.model

class DollarModel (
    val dollarvalue: String,
    val dollarvalueParalelo: String,
    val paraleloCompra: String,
    val paraleloVenta: String,
    val lastUpdated: Long = System.currentTimeMillis()
) {
}