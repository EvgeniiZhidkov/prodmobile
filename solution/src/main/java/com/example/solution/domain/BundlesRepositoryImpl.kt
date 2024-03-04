package com.example.solution.domain

import com.example.prod_playground.core.api.data.sources.local.BundlesLocalDataSource
import com.example.prod_playground.core.api.data.sources.remote.BundlesRemoteDataSource
import com.example.prod_playground.core.api.domain.models.BundleInfo
import com.example.prod_playground.core.api.domain.repositories.BundlesRepository


/**
 * Задача 4 | Список спецпредложений – получение данных
 */
class BundlesRepositoryImpl(
    private val localDataSource: BundlesLocalDataSource,
    private val remoteDataSource: BundlesRemoteDataSource
) : BundlesRepository {

    override fun getBundles(): List<BundleInfo> {
        var bundles: List<BundleInfo> = emptyList()
        if (localDataSource.getCachedBundles().isEmpty()) {
            bundles = remoteDataSource.loadBundles()
            localDataSource.cacheBundles(bundles)
        }else
        {
            val bundles: List<BundleInfo> = localDataSource.getCachedBundles()
        }
        return bundles
    }
}