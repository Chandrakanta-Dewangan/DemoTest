package com.freeagent.testapp.api.data

import com.freeagent.testapp.models.Currency


interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveCurrencies(currencies: Currency)
    suspend fun getLatestCurrencies(): List<Currency>
    suspend fun findById(id: Int): Currency
    suspend fun update(currency: Currency)
}