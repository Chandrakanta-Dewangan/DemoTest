package com.freeagent.testapp.api

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*
{
    "success": true,
    "timeseries": true,
    "start_date": "2022-11-14",
    "end_date": "2022-11-20",
    "base": "USD",
    "rates": {
        "2022-11-14": {
            "GBP": 0.851031,
            "EUR": 0.969095
        },
        "2022-11-15": {
            "GBP": 0.84145,
            "EUR": 0.965415
        },
        "2022-11-16": {
            "GBP": 0.839265,
            "EUR": 0.96207
        },
        "2022-11-17": {
            "GBP": 0.84322,
            "EUR": 0.965125
        },
        "2022-11-18": {
            "GBP": 0.840689,
            "EUR": 0.966604
        },
        "2022-11-19": {
            "GBP": 0.841149,
            "EUR": 0.966604
        },
        "2022-11-20": {
            "GBP": 0.841149,
            "EUR": 0.966604
        }
    }
}
 */
@Parcelize
data class HistoricalCurrency(
    val id: Int,
    @SerializedName("rates")
    @Expose
    val rates: Map<String,HistoricalRate>?,
    //val rates: Rate?,
    val base: String,
    val start_date: String,
    val end_date: String
) : Parcelable

@Parcelize
data class HistoricalRate(
    val rateId: Int,
    @SerializedName("USD")
    val usd: Double,
    @SerializedName("JPY")
    val jpy: Double,
    @SerializedName("AUD")
    val aud: Double,
    @SerializedName("GBP")
    val gbp: Double,
    @SerializedName("CAD")
    val cad: Double,
    @SerializedName("CHF")
    val chf: Double,
    @SerializedName("CNY")
    val cny: Double,
    @SerializedName("SEK")
    val sek: Double,
    @SerializedName("NZD")
    val nzd: Double
) : Parcelable