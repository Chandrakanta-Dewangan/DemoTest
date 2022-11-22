package com.freeagent.testapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoricalCurrency(
    val id: Int,
    val rates: Map<String,List<HistoricalRate>>?,
    val base: String,
    val start_date: String,
    val end_date: String
): Parcelable


