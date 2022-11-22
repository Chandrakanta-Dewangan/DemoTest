package com.freeagent.testapp.api
import android.os.Build
import androidx.annotation.RequiresApi
import com.freeagent.testapp.api.CurrencyRepository
import com.freeagent.testapp.models.HistoricalCurrency

class GetHistoricalCurrency(private val currenciesRepository: CurrencyRepository) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun invoke(symbols: String): HistoricalCurrency = currenciesRepository.getHistoricalCurrencies(symbols)
}