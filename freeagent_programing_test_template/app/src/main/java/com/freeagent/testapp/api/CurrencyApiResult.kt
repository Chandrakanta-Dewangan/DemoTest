package com.freeagent.testapp.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/*{
    "success": true,
    "timestamp": 1668198543,
    "base": "USD",
    "date": "2022-11-11",
    "rates": {
    "EUR": 0.965365,
    "GBP": 0.844705
}
}*/
@Parcelize
data class Currency(
    val id: Int,
    @SerializedName("rates")
    val rates: Rate?,
    val base: String,
    val date: String
) : Parcelable
//USD, EUR, JPY, GBP, AUD,
//CAD, CHF, CNY, SEK, NZD
@Parcelize
data class Rate(
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