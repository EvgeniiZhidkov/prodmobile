package com.example.solution.data.api

import com.example.prod_playground.core.api.data.api.OperationsApi
import com.example.prod_playground.core.api.data.json.JsonProvider
import com.example.prod_playground.core.api.domain.models.OperationCurrency
import com.example.prod_playground.core.api.domain.models.OperationDirection
import com.example.prod_playground.core.api.domain.models.OperationInfo
import org.json.JSONObject


/**
 * Задача 2 | Виджет пользователя – получение данных
 */
class DefaultOperationsApi(private val jsonProvider: JsonProvider) : OperationsApi {

    override fun getOperations(userId: String): List<OperationInfo> {
        return FromJsonToOperationInfoList(jsonProvider.operationsListJson)

    }
    fun FromJsonToOperationInfoList(a: String) : List<OperationInfo>
    {
        val jsonObject: JSONObject = JSONObject(a)
        val jsonArray = jsonObject.getJSONArray("operations")
        val ar : MutableList<OperationInfo> = emptyList<OperationInfo>().toMutableList()
        for (i in 0 until jsonArray.length()) {
            val opobj : JSONObject = jsonArray.getJSONObject(i)
            val value : String = jsonObject.getString("value")
            var cur : OperationCurrency
            var dir : OperationDirection
            if (jsonObject.getString("currency") != "special_point")
            {
                cur = OperationCurrency.RUB
            }else
            {
                cur = OperationCurrency.SPECIAL_POINTS
            }
            if (jsonObject.getString("direction") == "withdrawal")
            {
                dir = OperationDirection.WITHDRAWAL
            }else
            {
                dir = OperationDirection.RECEIPT
            }
            ar.add(OperationInfo(cur, value.toBigDecimal(), dir))
        }
        val oplist : List<OperationInfo> = ar.toList()
        return oplist

    }
}