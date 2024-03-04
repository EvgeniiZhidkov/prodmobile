package com.example.solution.data.api

import com.example.prod_playground.core.api.data.api.OffersApi
import com.example.prod_playground.core.api.data.json.JsonProvider
import com.example.prod_playground.core.api.domain.models.OfferBonus
import com.example.prod_playground.core.api.domain.models.OfferBonusType
import com.example.prod_playground.core.api.domain.models.OfferFullInfo
import com.example.prod_playground.core.api.domain.models.OfferInfo
import com.example.prod_playground.core.api.domain.models.OfferRestrictionData
import com.example.prod_playground.core.api.domain.models.OfferStatus
import com.example.prod_playground.core.api.domain.models.OfferTermsData
import com.example.prod_playground.core.api.domain.models.OfferType
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * Задача 4 | Список спецпредложений – получение данных
 */
class DefaultOffersApi(private val jsonProvider: JsonProvider) : OffersApi {

    override fun getOffers(userId: String, location: String, recommendation: Boolean): List<OfferInfo> {
        val OfferJson = jsonProvider.offersJson
        val jsonArray: JSONArray = JSONArray(OfferJson)
        val ar : MutableList<OfferInfo> = emptyList<OfferInfo>().toMutableList()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val offerImage = jsonObject.getString("offer_image")
            val supplierInfoObject = jsonObject.getJSONObject("supplier_info")
            val supplierName = supplierInfoObject.getString("name")
            val baseColor = supplierInfoObject.getString("base_color")
            val descriptionObject = jsonObject.getJSONObject("description")
            val shortDescription = descriptionObject.getString("short")
            val fullDescription = descriptionObject.getString("full")
            val status = jsonObject.getString("status")
            val type = jsonObject.getString("type")
            val bonusesObject = jsonObject.getJSONObject("bonuses")
            val bonusesType = bonusesObject.getString("type")
            val bonusesValue = bonusesObject.getString("value")
            val restrictionsArray = jsonObject.getJSONArray("restrictions")
            val rest : MutableList<OfferRestrictionData> = emptyList<OfferRestrictionData>().toMutableList()
            for (j in 0 until restrictionsArray.length()) {
                val restrictionObj: JSONObject = restrictionsArray.getJSONObject(j)
                val restrictionType = restrictionObj.getString("type")
                val valueObj = restrictionObj.getJSONObject("value")
                val bundlesIdsArray = valueObj.getJSONArray("bundles_ids")
                val bundlesIds: MutableList<String> = emptyList<String>().toMutableList()
                for (k in 0 until bundlesIdsArray.length()) {
                    val bundleId = bundlesIdsArray.getString(k)
                    bundlesIds.add(bundleId)
                }
                val newValue = valueObj.getInt("new_value")
                rest.add(OfferRestrictionData.ForBundles(bundlesIds, newValue.toString()))
                val restlist : List<OfferRestrictionData> = rest.toList()
                ar.add(OfferInfo(
                    userId, id, offerImage, supplierName, baseColor, shortDescription,
                    OfferStatus.fromString(status), OfferType.fromString(type),
                    OfferBonus(OfferBonusType.fromString(bonusesType), bonusesValue.toBigDecimal()), restlist))
            }
            val restlist : List<OfferRestrictionData> = rest.toList()
            ar.add(OfferInfo(
                userId, id, offerImage, supplierName, baseColor, shortDescription,
                OfferStatus.fromString(status), OfferType.fromString(type),
                OfferBonus(OfferBonusType.fromString(bonusesType), bonusesValue.toBigDecimal()), restlist))
        }
        val oflist : List<OfferInfo> = ar.toList()
        return oflist
    }

    override fun getFullOffer(id: String, userId: String): OfferFullInfo {
        val a : String = jsonProvider.offersJson
        val jsonObject = JSONObject(a)
        val id = jsonObject.getString("id")
        val offerImage = jsonObject.getString("offer_image")
        val supplierInfoObject = jsonObject.getJSONObject("supplier_info")
        val supplierName = supplierInfoObject.getString("name")
        val baseColor = supplierInfoObject.getString("base_color")
        val descriptionObject = jsonObject.getJSONObject("description")
        val shortDescription = descriptionObject.getString("short")
        val fullDescription = descriptionObject.getString("full")
        val status = jsonObject.getString("status")
        val type = jsonObject.getString("type")
        val bonusesObject = jsonObject.getJSONObject("bonuses")
        val bonusesType = bonusesObject.getString("type")
        val bonusesValue = bonusesObject.getString("value")
        val restrictionsArray = jsonObject.getJSONArray("restrictions")
        val rest: MutableList<OfferRestrictionData> =
            emptyList<OfferRestrictionData>().toMutableList()
        for (j in 0 until restrictionsArray.length()) {
            val restrictionObj: JSONObject = restrictionsArray.getJSONObject(j)
            val restrictionType = restrictionObj.getString("type")
            val valueObj = restrictionObj.getJSONObject("value")
            val bundlesIdsArray = valueObj.getJSONArray("bundles_ids")
            val bundlesIds: MutableList<String> = emptyList<String>().toMutableList()
            for (k in 0 until bundlesIdsArray.length()) {
                val bundleId = bundlesIdsArray.getString(k)
                bundlesIds.add(bundleId)
            }
            val newValue = valueObj.getInt("new_value")
            rest.add(OfferRestrictionData.ForBundles(bundlesIds, newValue.toString()))
            }
            val restlist: List<OfferRestrictionData> = rest.toList()
            val termsArray: JSONArray = jsonObject.getJSONArray("terms")
            val terms: MutableList<OfferTermsData> = emptyList<OfferTermsData>().toMutableList()
            for (k in 0 until termsArray.length()) {
                val termObj = termsArray.getJSONObject(k)
                val termType = termObj.getString("type")
                val termValue = termObj.getString("value")
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                terms.add(OfferTermsData.EndDate(LocalDate.parse(termValue, formatter)))
            }

            val promocode: String = jsonObject.getString("promocode")
            return OfferFullInfo(
                userId, id, offerImage, supplierName, baseColor, shortDescription,
                OfferStatus.fromString(status), OfferType.fromString(type),
                OfferBonus(OfferBonusType.fromString(bonusesType), bonusesValue.toBigDecimal()), restlist, terms, promocode)
        }
    }