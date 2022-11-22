package com.freeagent.testapp.api

import com.freeagent.testapp.api.helper.toDomainCurrency
import com.freeagent.testapp.models.Currency
import com.freeagent.testapp.models.HistoricalCurrency
import com.freeagent.testapp.api.helper.toDomainHistoricalCurrency
import com.freeagent.testapp.api.data.RemoteDataSource
import com.freeagent.testapp.utils.Constants

class CurrencyDataSource : RemoteDataSource {

    override suspend fun getLatestCurrencies(base: String): Currency {
        return CurrencyApi.service
            .getAllCurrencies(Constants.API_KEY, base, "USD,JPY,GBP,AUD,CAD,CHF,CNY,SEK,NZD").await().toDomainCurrency()
    }

    override suspend fun getHistoricalCurrencies(
        base: String,
        symbols:String,
        startDate: String,
        endDate: String
    ): HistoricalCurrency {
        return CurrencyApi.service
            .getHistoricalCurrency(Constants.API_KEY,base, symbols,startDate,endDate).await().toDomainHistoricalCurrency()
            //.getHistoricalCurrency("4mBhOMaRFEThp33buyBIxvFSjqloYL9Y",base, "GBP,EUR,JPY,BRL",startDate,endDate).await().toDomainHistoricalCurrency()
    }


}