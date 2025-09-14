package com.example.inmemoriam.features.dollar.domain.repository

import com.example.inmemoriam.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

interface IDollarRepository {
    suspend fun getDollar(): Flow<DollarModel>
}
