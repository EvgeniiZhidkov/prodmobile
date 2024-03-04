package com.example.solution.data.sources.local

import com.example.prod_playground.core.api.data.sources.local.UserInfoLocalDataSource
import com.example.prod_playground.core.api.domain.models.UserInfo

/**
 * Задача 2 | Виджет пользователя – получение данных
 */
class UserInfoLocalDataSourceImpl : UserInfoLocalDataSource {
    private var userInfo: UserInfo? = null
    override fun getUserInfo(): UserInfo? {
        return userInfo
    }

    override fun cacheUserInfo(userInfo: UserInfo)
    {
        this.userInfo = userInfo
    }
}