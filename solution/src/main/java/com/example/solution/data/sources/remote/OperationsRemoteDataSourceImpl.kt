package com.example.solution.data.sources.remote

import com.example.prod_playground.core.api.data.api.OperationsApi
import com.example.prod_playground.core.api.data.sources.remote.OperationsRemoteDataSource
import com.example.prod_playground.core.api.domain.models.OperationInfo
import com.example.solution.data.sources.local.OperationsLocalDataSourceImpl

/**
 * Задача 2 | Виджет пользователя – получение данных
 */
class OperationsRemoteDataSourceImpl(private val api: OperationsApi) : OperationsRemoteDataSource {

    override fun getOperations(): List<OperationInfo> {
        val id : String = "1234567"
        return api.getOperations(id)
    }
}