package com.freeagent.testapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoricalRate(
    val rateName: String?,
    val rateVal: Double?
    ):Parcelable
