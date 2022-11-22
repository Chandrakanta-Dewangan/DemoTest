package com.freeagent.testapp.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest")
    fun getAllCurrencies(@Header("apikey") apiKey: String, @Query("base") base: String, @Query("symbols") symbols: String): Deferred<Currency>

    @GET("timeseries")
    fun getHistoricalCurrency(@Header("apikey") apiKey: String, @Query("base") base: String, @Query("symbols") symbols: String,@Query("start_date") startDate: String,@Query("end_date") endDate: String): Deferred<HistoricalCurrency>

}

