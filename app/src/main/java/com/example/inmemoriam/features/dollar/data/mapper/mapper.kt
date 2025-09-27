package com.example.inmemoriam.features.dollar.data.mapper

import com.example.inmemoriam.features.dollar.data.database.entity.DollarEntity
import com.example.inmemoriam.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        dollarvalue = dollarvalue,
        dollarvalueParalelo = dollarvalueParalelo,
        paraleloCompra = paraleloCompra,
        paraleloVenta = paraleloVenta,
        lastUpdated = timestamp
    )
}

fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        dollarvalue = dollarvalue,
        dollarvalueParalelo = dollarvalueParalelo,
        paraleloCompra = paraleloCompra,
        paraleloVenta = paraleloVenta,
        timestamp = lastUpdated
    )
}
