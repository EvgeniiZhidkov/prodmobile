package com.example.solution.data.api

import com.example.prod_playground.core.api.data.api.BundlesApi
import com.example.prod_playground.core.api.data.json.JsonProvider
import com.example.prod_playground.core.api.domain.models.BundleInfo
import com.example.prod_playground.core.api.domain.models.OperationCurrency
import com.example.prod_playground.core.api.domain.models.OperationDirection
import com.example.prod_playground.core.api.domain.models.OperationInfo
import org.json.JSONArray

/**
 * Задача 4 | Список спецпредложений – получение данных
 */
class DefaultBundlesApi(private val jsonProvider: JsonProvider) : BundlesApi {

    var ans : List<BundleInfo> = emptyList()
    override fun getBundles(): List<BundleInfo> {
        if (ans.isEmpty())
        {
            ans = FromJsonToBundleInfoList(jsonProvider.bundlesJson)
        }
        return ans
    }

    fun FromJsonToBundleInfoList(a: String): List<BundleInfo>
    {
        val jsonArray: JSONArray = JSONArray(a)
        val ar : MutableList<BundleInfo> = emptyList<BundleInfo>().toMutableList()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val code : String = jsonObject.getString("code")
            val name : String = jsonObject.getString("name")
            val basecolor : String = jsonObject.getString("base_сolor")
            val secondcolor : String = jsonObject.getString("secondary_сolor")
            ar.add(BundleInfo(code, name, basecolor, secondcolor))
        }
        val oplist : List<BundleInfo> = ar.toList()
        return oplist
    }
}