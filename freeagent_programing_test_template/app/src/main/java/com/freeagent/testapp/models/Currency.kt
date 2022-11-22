package com.freeagent.testapp.models

import android.os.Parcelable
import com.freeagent.testapp.models.RatesData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Currency(
    val id: Int,
    val rates: List<RatesData>?,
    val base: String,
    val date: String
): Parcelable


