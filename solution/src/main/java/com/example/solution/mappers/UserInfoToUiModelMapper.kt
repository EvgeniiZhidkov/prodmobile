package com.example.solution.mappers

import com.example.prod_playground.core.api.domain.models.OperationCurrency
import com.example.prod_playground.core.api.domain.models.OperationDirection
import com.example.prod_playground.core.api.domain.models.OperationInfo
import com.example.prod_playground.core.api.domain.models.UserInfo
import com.example.solution.entities.UserInfoUiModel
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

/**
 * Задача 3 | Компонент "Полная информация о пользователе" – логика и подготовка отображения
 */
class UserInfoToUiModelMapper : (List<OperationInfo>, UserInfo) -> UserInfoUiModel {

    override fun invoke(operations: List<OperationInfo>, userInfo: UserInfo): UserInfoUiModel {

        var rub : BigDecimal = BigDecimal(0)
        var sp : BigDecimal = BigDecimal(0)
        operations.forEach{
            if (it.currency == OperationCurrency.RUB)
            {
                if (it.direction == OperationDirection.WITHDRAWAL)
                {
                    rub -= it.value
                }else
                {
                    rub += it.value
                }
            }else
            {
                if (it.direction == OperationDirection.WITHDRAWAL)
                {
                    sp -= it.value
                }else
                {
                    sp += it.value
                }
            }
        }
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
        val formattedString = formatter.format(sp)
        val decimalFormat = DecimalFormat("#,###.00")
        decimalFormat.applyPattern("#,###.00")
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formattedStringrub = decimalFormat.format(rub)
        return UserInfoUiModel(
            userName = userInfo.name + userInfo.surname,
            balance = formattedStringrub + " ₽",
            specialPoints = formattedString + " баллов",
        )
    }
}
