package com.freeagent.testapp.api.helper
import com.freeagent.testapp.models.Currency
import com.freeagent.testapp.models.HistoricalCurrency
import com.freeagent.testapp.models.RatesData
import com.freeagent.testapp.db.model.Currency as DomainCurrency
import com.freeagent.testapp.api.Currency as ServerCurrency
import com.freeagent.testapp.api.HistoricalCurrency as ServerHistoricalCurrency
import com.freeagent.testapp.api.Rate as ServerRate
import com.freeagent.testapp.models.RatesData as DomainRate
import com.freeagent.testapp.models.HistoricalRate
import com.google.gson.annotations.SerializedName

fun Currency.toRoomCurrency(): DomainCurrency =
    DomainCurrency(
        id,
        rates,
        base,
        date
    )


fun DomainCurrency.toDomainCurrency(): Currency =
    Currency(
        id,
        rates,
        base,
        date
    )

fun ServerCurrency.toDomainCurrency(): Currency =
    Currency(
        id,
        getRateData(this.rates) as List<RatesData>,
        base,
        date
    )

fun ServerHistoricalCurrency.toDomainHistoricalCurrency(): HistoricalCurrency =
    HistoricalCurrency(
        id,
        getData(rates),
        base,
        start_date,
        end_date
    )

fun getData(rate: Map<String, com.freeagent.testapp.api.HistoricalRate>?): Map<String, List<HistoricalRate>>? {
    val map = mutableMapOf<String, List<HistoricalRate>>()
    rate!!.forEach { (key, value) ->
        var ratesList:List<HistoricalRate> = listOf(
            HistoricalRate("USD",value.usd)
            , HistoricalRate("JPY",value.jpy)
            , HistoricalRate("AUD",value.aud)
            , HistoricalRate("GBP",value.gbp)
            , HistoricalRate("CAD",value.cad)
            , HistoricalRate("CHF",value.chf)
            , HistoricalRate("CNY",value.cny)
            , HistoricalRate("SEK",value.sek)
            , HistoricalRate("NZD",value.nzd)
        )
        map[key] = ratesList
    }
    return map
}



fun DomainRate.toRoomRate(): DomainRate {
    return DomainRate(rateName,rateVal)
}

fun DomainRate.toDomainRate(): DomainRate {
    return DomainRate(rateName,rateVal)
}

fun getRateData(rate: ServerRate?): List<RatesData?>? {
    rate?.let {
            var ratesList:List<RatesData> = listOf(
                RatesData("USD",it.usd)
                , RatesData("JPY",it.jpy)
                , RatesData("AUD",it.aud)
                , RatesData("GBP",it.gbp)
                , RatesData("CAD",it.cad)
                , RatesData("CHF",it.chf)
                , RatesData("CNY",it.cny)
                , RatesData("SEK",it.sek)
                , RatesData("NZD",it.nzd)
            )
            return ratesList }
    return null
}