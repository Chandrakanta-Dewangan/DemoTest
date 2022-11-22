package com.freeagent.testapp.api
import com.freeagent.testapp.api.CurrencyRepository
import com.freeagent.testapp.models.Currency

class GetLatestCurrencies(private val currenciesRepository: CurrencyRepository) {
    suspend fun invoke(): List<Currency> = currenciesRepository.getLatestCurrencies()
}