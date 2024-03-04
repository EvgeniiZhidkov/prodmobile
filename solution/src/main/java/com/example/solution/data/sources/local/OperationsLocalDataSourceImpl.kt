package com.example.solution.data.sources.local

import com.example.prod_playground.core.api.data.sources.local.OperationsLocalDataSource
import com.example.prod_playground.core.api.domain.models.OperationInfo

/**
 * Задача 2 | Виджет пользователя – получение данных
 */
class OperationsLocalDataSourceImpl : OperationsLocalDataSource {
    private var cachedOperations: List<OperationInfo>? = null
    override fun getOperations(): List<OperationInfo> {
        return cachedOperations ?: emptyList()
    }

    override fun cacheOperations(operations: List<OperationInfo>) : Unit {
        this.cachedOperations = operations;
    }
}