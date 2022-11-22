package com.freeagent.testapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RatesData(
    val rateName: String?,
    val rateVal: Double?
    ):Parcelable
