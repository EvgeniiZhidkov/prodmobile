package com.example.solution.data.api

import com.example.prod_playground.core.api.data.api.BannersApi
import com.example.prod_playground.core.api.data.json.JsonProvider
import com.example.prod_playground.core.api.domain.models.BannerItem
import org.json.JSONObject

/**
 * Задача 1 | Рекламный баннер – получение данных
 */
class DefaultBannersApi(private val jsonProvider: JsonProvider) : BannersApi {

    override fun getBanner(): BannerItem {
        val bannerJson: String = jsonProvider.bannerInfoJson
        return convertJsonToBannerItem(bannerJson)
    }

    private fun convertJsonToBannerItem(bannerJson: String): BannerItem {
        val json = jsonProvider.bannerInfoJson
        val jsonObject = JSONObject(json)

        val id = jsonObject.getString("id")
        val bannerInfoJson = jsonObject.getJSONObject("banner_info")
        val title = bannerInfoJson.getString("title")
        val description = bannerInfoJson.getString("description")
        val imageId = bannerInfoJson.getString("image_id")
        val backgroundColor = bannerInfoJson.getString("background_color")
        return BannerItem(id, title, description, imageId, backgroundColor)
    }

}