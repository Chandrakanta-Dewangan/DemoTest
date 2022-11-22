package com.freeagent.testapp.api.data

import com.freeagent.testapp.models.Currency
import com.freeagent.testapp.models.HistoricalCurrency


interface RemoteDataSource {
    suspend fun getLatestCurrencies(base: String): Currency
    suspend fun getHistoricalCurrencies(base: String,symbols: String, startDate: String, endDate : String): HistoricalCurrency
}