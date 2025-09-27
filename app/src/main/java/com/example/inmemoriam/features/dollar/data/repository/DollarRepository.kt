package com.example.inmemoriam.features.dollar.data.repository

import com.example.inmemoriam.features.dollar.data.datasource.DollarLocalDataSource
import com.example.inmemoriam.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.example.inmemoriam.features.dollar.domain.model.DollarModel
import com.example.inmemoriam.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


class DollarRepository(
    val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    val localDataSource: DollarLocalDataSource
): IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> {
//        return flow {
//            emit(DollarModel("123", "456"))
//        }
        return realTimeRemoteDataSource.getDollarUpdates()
            .onEach {
                localDataSource.insert(it)
            }

    }
}