package com.example.solution.data.api

import com.example.prod_playground.core.api.data.api.UsersApi
import com.example.prod_playground.core.api.data.json.JsonProvider
import com.example.prod_playground.core.api.domain.models.UserInfo
import org.json.JSONObject

/**
 * Задача 2 | Виджет пользователя – получение данных
 */
class DefaultUsersApi(private val jsonProvider: JsonProvider) : UsersApi {

    override fun getUser(userId: String): UserInfo {
        return FromJsonToUser(jsonProvider.userJson)
    }

    fun FromJsonToUser(x: String) : UserInfo
    {
        val json : JSONObject = JSONObject(x)
        val id = json.getString("id")
        val name = json.getString("name")
        val surname = json.getString("surname")
        val bund = json.getString("bundle_сode")
        return UserInfo(id, name, surname, bund)
    }
}